package com.cherry.guessthething.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cherry.guessthething.domain.model.Cartoon

@Database(entities = [Cartoon::class], version = 1)
abstract class CartoonDatabase : RoomDatabase() {

    abstract fun cartoonDao(): CartoonDao

    companion object {
        const val DB_NAME = "main.db"
        private var db: CartoonDatabase? = null

        @Synchronized
        fun getInstance(application: Application): CartoonDatabase {
            db?.let {
                return it
            }
            val instance = Room.databaseBuilder(
                application,
                CartoonDatabase::class.java,
                DB_NAME
            ).build()
            db = instance
            return instance
        }
    }
}