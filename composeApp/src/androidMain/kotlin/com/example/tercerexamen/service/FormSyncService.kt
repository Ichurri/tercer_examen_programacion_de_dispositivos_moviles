package com.example.tercerexamen.service

import android.content.Context
import android.util.Log
import com.example.tercerexamen.data.database.DatabaseProvider
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FormSyncService(private val context: Context) {
    private val db = DatabaseProvider.getInstance(context)
    private val firebaseDb = Firebase.database
    private val formRef = firebaseDb.getReference("form_data")

    suspend fun syncFormData() {
        try {
            val formData = db.formAutosaveDao().getFormDataSync()

            if (formData == null || formData.synced) {
                Log.d(TAG, "Form already synced or empty")
                return
            }

            Log.d(TAG, "Syncing form data to Firebase")

            try {
                val data = mapOf(
                    "content" to formData.formData,
                    "lastSavedAt" to formData.lastSavedAt,
                    "syncedAt" to System.currentTimeMillis()
                )

                formRef.setValue(data).await()
                db.formAutosaveDao().markAsSynced()

                Log.d(TAG, "Form synced successfully")
            } catch (e: Exception) {
                Log.e(TAG, "Failed to sync form: ${e.message}")
                throw e
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error syncing form: ${e.message}", e)
        }
    }

    companion object {
        private const val TAG = "FormSyncService"
        private var instance: FormSyncService? = null

        fun getInstance(context: Context): FormSyncService {
            if (instance == null) {
                instance = FormSyncService(context)
            }
            return instance!!
        }
    }
}

