package com.univ.data.db.memo

import com.univ.data.model.Memo
import kotlinx.coroutines.flow.Flow

interface MemoDataSource {
    fun getAllMemo(): Flow<List<Memo>>
    fun getBetweenMemo(date1: Long, date2: Long): Flow<List<Memo>>
    fun getMemo(id: Long): Flow<Memo?>
    suspend fun insertMemo(memo: Memo)
    suspend fun updateMemo(id: Long, memo: String)
    suspend fun deleteMemo(id: Long)
    suspend fun deleteAllMemo()
}