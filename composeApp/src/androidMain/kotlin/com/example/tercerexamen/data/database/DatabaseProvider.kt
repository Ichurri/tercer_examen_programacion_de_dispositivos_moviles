package com.example.tercerexamen.data.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var instance: TercerExamenDatabase? = null

    fun getInstance(context: Context): TercerExamenDatabase {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.applicationContext,
                TercerExamenDatabase::class.java,
                "tercer_examen.db"
            ).build()
        }
        return instance!!
    }
}

