package com.univ.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.univ.data.db.emotion.EmotionDao
import com.univ.data.db.memo.MemoDao
import com.univ.data.model.Emotion
import com.univ.data.model.Memo

@Database(entities = [Memo::class, Emotion::class], version = 1, exportSchema = false)
abstract class DoraDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao
    abstract fun emotionDao(): EmotionDao
}