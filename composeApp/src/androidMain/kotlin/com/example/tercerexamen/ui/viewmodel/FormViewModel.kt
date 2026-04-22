package com.example.tercerexamen.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tercerexamen.data.database.DatabaseProvider
import com.example.tercerexamen.data.entity.FormAutosave
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FormViewModel(private val context: Context) : ViewModel() {
    val formData = mutableStateOf("")
    private val db = DatabaseProvider.getInstance(context)

    init {
        loadFormData()
        startAutosave()
    }

    private fun loadFormData() {
        viewModelScope.launch {
            try {
                val saved = db.formAutosaveDao().getFormDataSync()
                if (saved != null) {
                    formData.value = saved.formData
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun startAutosave() {
        viewModelScope.launch {
            while (true) {
                delay(30_000) // 30 segundos
                saveFormAutomatically()
            }
        }
    }

    private suspend fun saveFormAutomatically() {
        try {
            if (formData.value.isNotEmpty()) {
                val form = FormAutosave(
                    id = 1,
                    formData = formData.value,
                    lastSavedAt = System.currentTimeMillis(),
                    synced = false
                )
                db.formAutosaveDao().saveForm(form)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun submitForm() {
        viewModelScope.launch {
            try {
                val form = FormAutosave(
                    id = 1,
                    formData = formData.value,
                    lastSavedAt = System.currentTimeMillis(),
                    synced = false
                )
                db.formAutosaveDao().saveForm(form)
                // Aquí se podría sincronizar con Firebase más tarde
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun clearForm() {
        viewModelScope.launch {
            formData.value = ""
            db.formAutosaveDao().clearForm()
        }
    }
}


