package com.example.a7minworkout.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(historyEntity: HistoryEntity)

    @Query("select * from `history-table`")
    fun getList(): Flow<List<HistoryEntity>>
}