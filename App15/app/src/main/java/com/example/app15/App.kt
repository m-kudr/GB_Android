package com.example.app15

import android.app.Application
import androidx.room.Room

class App: Application() {
    lateinit var db: WordDatabase

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            WordDatabase::class.java,
            "db"
        ).build()
    }
}