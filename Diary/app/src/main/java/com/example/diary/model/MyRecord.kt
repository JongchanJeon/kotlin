package com.example.diary.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyRecord(
    @PrimaryKey(autoGenerate = true) val rid:Int,
    @ColumnInfo val diary:String,
    @ColumnInfo val time:String
)