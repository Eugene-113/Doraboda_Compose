package com.univ.data.db.emotion

import com.univ.data.model.Emotion
import kotlinx.coroutines.flow.Flow

interface EmotionDataSource {
    fun getAllEmotion(): Flow<List<Emotion>>
    fun getBetweenEmotion(date1: Long, date2: Long): Flow<List<Emotion>>
    fun getEmotion(id: Long): Flow<Emotion?>
    suspend fun insertEmotion(emotion: Emotion)
    suspend fun updateEmotion(id: Long, emotion: String)
    suspend fun deleteEmotion(id: Long)
    suspend fun deleteAllEmotion()
}