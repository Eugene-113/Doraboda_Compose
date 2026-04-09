package com.univ.data.db.memo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.univ.data.model.Memo
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMemo(memo: Memo)

    @Query("SELECT * FROM memoTable WHERE id = :id")
    fun getMemo(id: Long): Flow<Memo?>

    @Query("UPDATE memoTable SET memo = :memo WHERE id = :id")
    fun updateMemo(id: Long, memo: String)

    @Query("DELETE FROM memoTable WHERE id = :id")
    fun deleteData(id: Long)

    @Query("DELETE FROM memoTable")
    fun deleteAllMemo()

    @Query("SELECT * FROM memoTable WHERE id BETWEEN :date1 AND :date2")
    fun getBetweenMemo(date1: Long, date2: Long): Flow<List<Memo>>

    @Query("SELECT * FROM memoTable")
    fun getAllMemo(): Flow<List<Memo>>
}