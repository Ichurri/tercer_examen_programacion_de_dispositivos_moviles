package com.example.tercerexamen.util

import android.content.Context
import androidx.work.*
import com.example.tercerexamen.worker.EventSyncWorker
import com.example.tercerexamen.worker.FormSyncWorker
import java.util.concurrent.TimeUnit

object WorkManagerHelper {

    fun scheduleSyncWorkers(context: Context) {
        // Sincronización de eventos cada 15 minutos
        val eventSyncRequest = PeriodicWorkRequestBuilder<EventSyncWorker>(
            15, TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "event_sync",
                ExistingPeriodicWorkPolicy.KEEP,
                eventSyncRequest
            )

        // Sincronización de formularios cada 15 minutos
        val formSyncRequest = PeriodicWorkRequestBuilder<FormSyncWorker>(
            15, TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "form_sync",
                ExistingPeriodicWorkPolicy.KEEP,
                formSyncRequest
            )
    }
}

