package com.univ.data.repository

import com.univ.data.db.Mapper.toData
import com.univ.data.db.Mapper.toDomain
import com.univ.data.db.emotion.EmotionDataSource
import com.univ.data.model.Emotion
import com.univ.domain.model.EmotionData
import com.univ.domain.repository.EmotionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.collections.map

class EmotionRepositoryImpl @Inject constructor(val dataSource: EmotionDataSource) : EmotionRepository {
    override fun getAllEmotion(): Flow<List<EmotionData>> {
        return dataSource.getAllEmotion().map { model -> model.map(Emotion::toDomain) }
    }

    override fun getBetweenEmotion(
        date1: Long,
        date2: Long,
    ): Flow<List<EmotionData>> {
        return dataSource.getBetweenEmotion(date1, date2).map { model -> model.map(Emotion::toDomain) }
    }

    override fun getEmotion(id: Long): Flow<EmotionData?> {
        return dataSource.getEmotion(id).map { model -> model?.toDomain() }
    }

    override suspend fun insertEmotion(emotion: EmotionData) {
        dataSource.insertEmotion(emotion.toData())
    }

    override suspend fun updateEmotion(id: Long, emotion: String) {
        dataSource.updateEmotion(id, emotion)
    }

    override suspend fun deleteEmotion(id: Long) {
        dataSource.deleteEmotion(id)
    }

    override suspend fun deleteAllEmotion() {
        dataSource.deleteAllEmotion()
    }

}