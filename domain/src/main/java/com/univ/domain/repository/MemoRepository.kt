package com.univ.domain.repository

import com.univ.domain.model.MemoData
import kotlinx.coroutines.flow.Flow

interface MemoRepository {
    fun getAllMemo(): Flow<List<MemoData>>
    fun getBetweenMemo(date1: Long, date2: Long): Flow<List<MemoData>>
    fun getMemo(id: Long): Flow<MemoData?>
    suspend fun insertMemo(memo: MemoData)
    suspend fun updateMemo(id: Long, memo: String)
    suspend fun deleteMemo(id: Long)
    suspend fun deleteAllMemo()
}