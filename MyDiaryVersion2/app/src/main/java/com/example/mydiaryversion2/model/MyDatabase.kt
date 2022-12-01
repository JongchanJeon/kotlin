package com.example.mydiaryversion2.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mydiaryversion2.ReadActivity
import com.example.mydiaryversion2.ReadAdapter
import com.example.mydiaryversion2.WriteActivity

@Database(entities = [MyRecord::class], version=1)
abstract class MyDatabase:RoomDatabase() {
    abstract fun myDao():MyDao

    companion object{
        private var INSTANCE:MyDatabase?=null
        @JvmStatic
        fun getInstance(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "diary.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
