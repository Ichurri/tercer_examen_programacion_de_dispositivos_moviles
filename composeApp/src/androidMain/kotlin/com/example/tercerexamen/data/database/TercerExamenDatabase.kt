package com.example.tercerexamen.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tercerexamen.data.dao.AppEventDao
import com.example.tercerexamen.data.dao.FormAutosaveDao
import com.example.tercerexamen.data.entity.AppEvent
import com.example.tercerexamen.data.entity.FormAutosave

@Database(
    entities = [AppEvent::class, FormAutosave::class],
    version = 1,
    exportSchema = false
)
abstract class TercerExamenDatabase : RoomDatabase() {
    abstract fun appEventDao(): AppEventDao
    abstract fun formAutosaveDao(): FormAutosaveDao
}

