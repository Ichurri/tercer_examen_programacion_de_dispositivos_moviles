package com.example.tercerexamen.service

import android.content.Context
import android.util.Log
import com.example.tercerexamen.data.database.DatabaseProvider
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class EventSyncService(private val context: Context) {
    private val db = DatabaseProvider.getInstance(context)
    private val firebaseDb = Firebase.database
    private val eventsRef = firebaseDb.getReference("app_events")

    suspend fun syncUnsyncedEvents() {
        try {
            val unsyncedEvents = db.appEventDao().getUnsyncedEvents()

            if (unsyncedEvents.isEmpty()) {
                Log.d(TAG, "No unsynced events found")
                return
            }

            Log.d(TAG, "Syncing ${unsyncedEvents.size} events to Firebase")

            for (event in unsyncedEvents) {
                try {
                    val eventData = mapOf(
                        "timestamp" to event.timestamp,
                        "eventType" to event.eventType,
                        "syncedAt" to System.currentTimeMillis()
                    )

                    eventsRef.child(event.id.toString()).setValue(eventData).await()
                    db.appEventDao().markAsSynced(event.id)

                    Log.d(TAG, "Event ${event.id} synced successfully")
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to sync event ${event.id}: ${e.message}")
                }
            }

            Log.d(TAG, "All events synced successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Error syncing events: ${e.message}", e)
        }
    }

    companion object {
        private const val TAG = "EventSyncService"
        private var instance: EventSyncService? = null

        fun getInstance(context: Context): EventSyncService {
            if (instance == null) {
                instance = EventSyncService(context)
            }
            return instance!!
        }
    }
}

