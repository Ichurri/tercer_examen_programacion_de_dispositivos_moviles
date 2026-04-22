package com.example.tercerexamen.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.tercerexamen.service.FormSyncService
import kotlin.Result.Companion.success

class FormSyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            val syncService = FormSyncService.getInstance(applicationContext)
            syncService.syncFormData()
            success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
}

