package com.example.tercerexamen.data.dao

import androidx.room.*
import com.example.tercerexamen.data.entity.FormAutosave
import kotlinx.coroutines.flow.Flow

@Dao
interface FormAutosaveDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveForm(form: FormAutosave)

    @Query("SELECT * FROM form_autosave WHERE id = 1")
    fun getFormData(): Flow<FormAutosave?>

    @Query("SELECT * FROM form_autosave WHERE id = 1")
    suspend fun getFormDataSync(): FormAutosave?

    @Query("UPDATE form_autosave SET synced = 1 WHERE id = 1")
    suspend fun markAsSynced()

    @Query("DELETE FROM form_autosave")
    suspend fun clearForm()
}

