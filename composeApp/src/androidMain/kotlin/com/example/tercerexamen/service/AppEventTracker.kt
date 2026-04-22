package com.example.tercerexamen.service

import android.content.Context
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.tercerexamen.data.database.DatabaseProvider
import com.example.tercerexamen.data.entity.AppEvent
import kotlinx.coroutines.launch

class AppEventTracker(private val context: Context) : DefaultLifecycleObserver {

    init {
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    override fun onCreate(owner: LifecycleOwner) {
        recordEvent("OPEN")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        recordEvent("CLOSE")
    }

    private fun recordEvent(eventType: String) {
        ProcessLifecycleOwner.get().lifecycleScope.launch {
            try {
                val db = DatabaseProvider.getInstance(context)
                val event = AppEvent(
                    timestamp = System.currentTimeMillis(),
                    eventType = eventType,
                    synced = false
                )
                db.appEventDao().insert(event)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private var instance: AppEventTracker? = null

        fun getInstance(context: Context): AppEventTracker {
            if (instance == null) {
                instance = AppEventTracker(context)
            }
            return instance!!
        }
    }
}


