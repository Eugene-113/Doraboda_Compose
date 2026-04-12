package com.univ.data.db.emotion

import com.univ.data.model.Emotion
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmotionDataSourceImpl @Inject constructor(private val dao: EmotionDao): EmotionDataSource {
    override fun getAllEmotion(): Flow<List<Emotion>> {
        return dao.getAllEmotion()
    }

    override fun getBetweenEmotion(
        date1: Long,
        date2: Long,
    ): Flow<List<Emotion>> {
        return dao.getBetweenEmotion(date1, date2)
    }

    override fun getEmotion(id: Long): Flow<Emotion?> {
        return dao.getEmotion(id)
    }

    override suspend fun insertEmotion(emotion: Emotion) {
        dao.insertEmotion(emotion)
    }

    override suspend fun updateEmotion(id: Long, emotion: String) {
        dao.updateEmotion(id, emotion)
    }

    override suspend fun deleteEmotion(id: Long) {
        dao.deleteEmotion(id)
    }

    override suspend fun deleteAllEmotion() {
        dao.deleteAllEmotion()
    }

}