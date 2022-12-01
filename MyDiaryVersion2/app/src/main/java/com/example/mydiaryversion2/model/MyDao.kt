package com.example.mydiaryversion2.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
@Dao
interface MyDao {
    @Query("select * from MyRecord order by date desc")
    fun selectAll():List<MyRecord>

    @Query("select story from MyRecord where date= :date")
    fun select(date: String):String

    @Insert(onConflict = IGNORE)
    suspend fun insert(record:MyRecord)
    @Delete
    suspend fun delete(record:MyRecord)

    @Query("DELETE FROM MyRecord WHERE date = :date")
    fun deleteByDate(date: String)

    @Query("UPDATE MyRecord SET STORY=:story WHERE date =:date")
    fun updateByDate(story: String,date: String)

}
