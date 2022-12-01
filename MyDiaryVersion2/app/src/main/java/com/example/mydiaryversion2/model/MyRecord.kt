package com.example.mydiaryversion2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class MyRecord(
    @PrimaryKey val date:String,
    @ColumnInfo val story:String
)