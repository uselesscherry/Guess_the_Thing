package com.cherry.guessthething.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moya_utoma.color_data.data.model.NamedColor
import com.moya_utoma.color_data.data.local.ColorDao

@Database(entities = [NamedColor::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun colorDao(): ColorDao

    companion object {
        const val DB_NAME = "app.db"
    }
}