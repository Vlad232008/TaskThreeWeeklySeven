package com.example.taskthreeweeklyseven.data.db

import androidx.room.Room
import com.example.taskthreeweeklyseven.MainApp

object DataBaseFactory {
    private val dataBase: DataBase by lazy {
        Room.databaseBuilder(MainApp.ContextHolder.context, DataBase::class.java, "favorite.db")
            .build()
    }

    fun create(): DataBase = dataBase
}