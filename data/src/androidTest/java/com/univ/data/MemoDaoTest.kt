package com.univ.data

import com.univ.data.model.Memo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class MemoDaoTest : DatabaseTest(){
    @Test
    fun insertAndGet() = runTest {
        val memo = testMemoModel(2002, 2, 2, "Hi")
        memoDao.insertMemo(memo)
        assertEquals(memo, memoDao.getMemo(dateToLong(2002, 2, 2)).first())
    }

    @Test
    fun getBetweenSize() = runTest {
        insertMemos()
        val date1 = dateToLong(2001, 1, 1)
        val date2 = dateToLong(2005, 3, 2)
        val size = memoDao.getBetweenMemo(date1, date2).first().size
        assertEquals(2, size)
    }

    @Test
    fun updateOne() = runTest {
        val date = dateToLong(2002, 2, 2)
        val memo = testMemoModel(2002, 2, 2, "Hi")
        memoDao.insertMemo(memo)
        memoDao.updateMemo(date, "lol")
        assertEquals("lol", memoDao.getMemo(date).first()?.memo)
    }

    @Test
    fun deleteAllMemos() = runTest {
        insertMemos()
        memoDao.deleteAllMemo()
        assertEquals(0, memoDao.getAllMemo().first().size)
    }

    private suspend fun insertMemos(){
        val memos = listOf(
            testMemoModel(2002, 2, 2, "Hi"),
            testMemoModel(2005, 3, 2, "Hello"),
            testMemoModel(2007, 4, 2, "World")
        )
        for(memo in memos){
            memoDao.insertMemo(memo)
        }
    }

    private fun testMemoModel(
        year: Int,
        month: Int,
        day: Int,
        memo: String
    ) = Memo(
        ID = dateToLong(year, month, day),
        memo = memo
    )
}