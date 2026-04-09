package com.univ.calendar.mapper

import com.univ.calendar.model.MemoItem
import com.univ.domain.model.MemoData
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

fun MemoData.toUI(): MemoItem {
    val date = Instant.ofEpochMilli(time)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
    return MemoItem(
        year = date.year,
        month = date.monthValue,
        day = date.dayOfMonth,
        memo = memo
    )
}

fun MemoItem.toDomain() = MemoData(
    time = LocalDate.of(year, month, day)
        .atStartOfDay(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli(),
    memo = memo
)