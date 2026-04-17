package com.univ.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.univ.data.db.DoraDatabase
import com.univ.data.db.emotion.EmotionDao
import com.univ.data.db.memo.MemoDao
import org.junit.After
import org.junit.Before
import java.time.LocalDate
import java.time.ZoneId

abstract class DatabaseTest {
    private lateinit var db: DoraDatabase
    lateinit var emotionDao: EmotionDao
    lateinit var memoDao: MemoDao

    @Before
    fun setUp(){
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DoraDatabase::class.java
            ).build()
        emotionDao = db.emotionDao()
        memoDao = db.memoDao()
    }

    @After
    fun tearDown(){
        db.close()
    }

    fun dateToLong(year: Int, month: Int, day: Int): Long
            = LocalDate.of(year, month, day)
        .atStartOfDay(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli()
}