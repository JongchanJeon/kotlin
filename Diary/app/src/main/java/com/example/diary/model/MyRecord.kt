package com.example.diary.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyRecord(
    @ColumnInfo val diary:String,
    @PrimaryKey val time:String
)