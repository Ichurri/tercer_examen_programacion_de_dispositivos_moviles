package com.example.tercerexamen.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "form_autosave")
data class FormAutosave(
    @PrimaryKey
    val id: Long = 1,
    val formData: String,
    val lastSavedAt: Long,
    val synced: Boolean = false
)

