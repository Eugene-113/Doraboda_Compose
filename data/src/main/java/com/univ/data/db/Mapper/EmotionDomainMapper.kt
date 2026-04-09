package com.univ.data.db.Mapper

import com.univ.data.model.Emotion
import com.univ.domain.model.EmotionData

fun Emotion.toDomain(): EmotionData = EmotionData(time = ID, emotion = emotion)
fun EmotionData.toData(): Emotion = Emotion(ID = time, emotion = emotion)