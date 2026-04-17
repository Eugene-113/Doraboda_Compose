package com.univ.data

import com.univ.data.model.Emotion
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class EmotionDaoTest : DatabaseTest() {

    @Test
    fun insertAndGet() = runTest {
        val emotion = testEmotionModel(2002, 2, 2, "sad")
        emotionDao.insertEmotion(emotion)
        assertEquals(emotion, emotionDao.getEmotion(dateToLong(2002, 2, 2)).first())
    }

    @Test
    fun getBetweenSize() = runTest {
        insertEmotions()
        val date1 = dateToLong(2001, 1, 1)
        val date2 = dateToLong(2005, 3, 2)
        val size = emotionDao.getBetweenEmotion(date1, date2).first().size
        assertEquals(2, size)
    }

    @Test
    fun updateOne() = runTest {
        val date = dateToLong(2002, 2, 2)
        val emotion = testEmotionModel(2002, 2, 2, "sad")
        emotionDao.insertEmotion(emotion)
        emotionDao.updateEmotion(date, "normal")
        assertEquals("normal", emotionDao.getEmotion(date).first()?.emotion)
    }

    @Test
    fun deleteAllEmotions() = runTest {
        insertEmotions()
        emotionDao.deleteAllEmotion()
        assertEquals(0, emotionDao.getAllEmotion().first().size)
    }

    private suspend fun insertEmotions(){
        val emotions = listOf(
            testEmotionModel(2002, 2, 2, "sad"),
            testEmotionModel(2005, 3, 2, "normal"),
            testEmotionModel(2007, 4, 2, "joyful")
        )
        for(emotion in emotions){
            emotionDao.insertEmotion(emotion)
        }
    }

    private fun testEmotionModel(
        year: Int,
        month: Int,
        day: Int,
        emotion: String
    ) = Emotion(
        ID = dateToLong(year, month, day),
        emotion = emotion
    )
}