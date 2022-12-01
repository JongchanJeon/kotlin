package com.example.diary.model


import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE

@Dao
interface MyDao  {
    @Query("select * from MyRecord order by time desc")
    fun selectAll():List<MyRecord>

    @Query("select diary from MyRecord where time = :time")
    fun selectTime(time: String):String

    @Update
    suspend fun update(record: MyRecord)

    @Insert(onConflict = IGNORE)
    suspend fun insert(record: MyRecord)

    @Delete
    suspend fun delete(record: MyRecord)

}