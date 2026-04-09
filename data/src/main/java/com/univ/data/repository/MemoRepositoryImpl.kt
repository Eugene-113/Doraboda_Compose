package com.univ.data.repository

import com.univ.data.db.Mapper.toData
import com.univ.data.db.Mapper.toDomain
import com.univ.data.db.memo.MemoDataSource
import com.univ.data.model.Memo
import com.univ.domain.model.MemoData
import com.univ.domain.repository.MemoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MemoRepositoryImpl(private val dataSource: MemoDataSource) : MemoRepository {
    override fun getAllMemo(): Flow<List<MemoData>> {
        return dataSource.getAllMemo().map { model -> model.map(Memo::toDomain) }
    }

    override fun getBetweenMemo(
        date1: Long,
        date2: Long,
    ): Flow<List<MemoData>> {
        return dataSource.getBetweenMemo(date1, date2).map { model -> model.map(Memo::toDomain) }
    }

    override fun getMemo(id: Long): Flow<MemoData?> {
        return dataSource.getMemo(id).map { model -> model?.toDomain() }
    }

    override suspend fun insertMemo(memo: MemoData) {
        dataSource.insertMemo(memo.toData())
    }

    override suspend fun updateMemo(id: Long, memo: String) {
        dataSource.updateMemo(id, memo)
    }

    override suspend fun deleteMemo(id: Long) {
        dataSource.deleteMemo(id)
    }

    override suspend fun deleteAllMemo() {
        dataSource.deleteAllMemo()
    }
}