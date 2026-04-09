package com.univ.calendar.mapper

import com.univ.calendar.model.EmotionItem
import com.univ.domain.model.EmotionData
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

fun EmotionData.toUI(): EmotionItem {
    val date = Instant.ofEpochMilli(time)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
    return EmotionItem(
        year = date.year,
        month = date.monthValue,
        day = date.dayOfMonth,
        emotion = emotion
    )
}

fun EmotionItem.toDomain() = EmotionData(
    time = LocalDate.of(year, month, day)
        .atStartOfDay(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli(),
    emotion = emotion
)