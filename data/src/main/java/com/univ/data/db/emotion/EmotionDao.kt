package com.univ.data.db.emotion

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.univ.data.model.Emotion
import kotlinx.coroutines.flow.Flow

@Dao
interface EmotionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmotion(emotion: Emotion)

    @Query("SELECT * FROM emotionTable WHERE id = :id")
    fun getEmotion(id: Long): Flow<Emotion?>

    @Query("UPDATE emotionTable SET emotion = :emotion WHERE id = :id")
    fun updateEmotion(id: Long, emotion: String)

    @Query("DELETE FROM emotionTable WHERE id = :id")
    fun deleteEmotion(id: Long)

    @Query("DELETE FROM emotionTable")
    fun deleteAllEmotion()

    @Query("SELECT * FROM emotionTable WHERE id BETWEEN :date1 AND :date2")
    fun getBetweenEmotion(date1: Long, date2: Long): Flow<List<Emotion>>

    @Query("SELECT * FROM emotionTable")
    fun getAllEmotion(): Flow<List<Emotion>>
}