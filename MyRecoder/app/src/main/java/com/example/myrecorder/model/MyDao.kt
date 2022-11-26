package com.example.myrecorder.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDao {
    @Query("select * from MyRecord order by rid desc")
        fun selectAll(): Flow<List<MyRecord>>

        @Insert(onConflict = IGNORE)
        suspend fun insert(record:MyRecord)

        @Delete
        suspend fun delete(record:MyRecord)
}