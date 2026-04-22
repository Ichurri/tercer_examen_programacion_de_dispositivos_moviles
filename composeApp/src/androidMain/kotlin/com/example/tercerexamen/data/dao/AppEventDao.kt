package com.example.tercerexamen.data.dao

import androidx.room.*
import com.example.tercerexamen.data.entity.AppEvent
import kotlinx.coroutines.flow.Flow

@Dao
interface AppEventDao {
    @Insert
    suspend fun insert(event: AppEvent)

    @Query("SELECT * FROM app_events WHERE synced = 0 ORDER BY timestamp DESC")
    suspend fun getUnsyncedEvents(): List<AppEvent>

    @Query("SELECT * FROM app_events ORDER BY timestamp DESC LIMIT :limit")
    fun getRecentEvents(limit: Int = 100): Flow<List<AppEvent>>

    @Update
    suspend fun update(event: AppEvent)

    @Query("UPDATE app_events SET synced = 1 WHERE id = :id")
    suspend fun markAsSynced(id: Long)

    @Delete
    suspend fun delete(event: AppEvent)
}

