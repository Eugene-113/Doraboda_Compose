package com.univ.data.db.memo

import com.univ.data.model.Memo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemoDataSourceImpl @Inject constructor(private val dao: MemoDao) : MemoDataSource {
    override fun getAllMemo(): Flow<List<Memo>> {
        return dao.getAllMemo()
    }

    override fun getBetweenMemo(
        date1: Long,
        date2: Long,
    ): Flow<List<Memo>> {
        return dao.getBetweenMemo(date1, date2)
    }

    override fun getMemo(id: Long): Flow<Memo?> {
        return dao.getMemo(id)
    }

    override suspend fun insertMemo(memo: Memo) {
        dao.insertMemo(memo)
    }

    override suspend fun updateMemo(id: Long, memo: String) {
        dao.updateMemo(id, memo)
    }

    override suspend fun deleteMemo(id: Long) {
        dao.deleteData(id)
    }

    override suspend fun deleteAllMemo() {
        dao.deleteAllMemo()
    }

}