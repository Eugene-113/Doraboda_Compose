package com.univ.domain.repository

import com.univ.domain.model.EmotionData
import kotlinx.coroutines.flow.Flow

interface EmotionRepository {
    fun getAllEmotion(): Flow<List<EmotionData>>
    fun getBetweenEmotion(date1: Long, date2: Long): Flow<List<EmotionData>>
    fun getEmotion(id: Long): Flow<EmotionData?>
    suspend fun insertEmotion(emotion: EmotionData)
    suspend fun updateEmotion(id: Long, emotion: String)
    suspend fun deleteEmotion(id: Long)
    suspend fun deleteAllEmotion()
}