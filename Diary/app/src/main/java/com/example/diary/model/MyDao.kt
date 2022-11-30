package com.example.diary.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query


@Dao
interface MyDao  {
    @Query("select * from MyRecord order by time desc")
    fun selectAll():List<MyRecord>


    @Insert(onConflict = IGNORE)
    suspend fun insert(record: MyRecord)

    @Delete
    suspend fun delete(record: MyRecord)

}