package com.example.tercerexamen.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.tercerexamen.data.database.DatabaseProvider

class EventSyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            val db = DatabaseProvider.getInstance(applicationContext)
            val unsyncedEvents = db.appEventDao().getUnsyncedEvents()

            if (unsyncedEvents.isNotEmpty()) {
                // Aquí se podría sincronizar con Firebase más adelante
                // Por ahora solo marcaremos como sincronizados
                for (event in unsyncedEvents) {
                    db.appEventDao().markAsSynced(event.id)
                }
            }

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
}


