package com.univ.calendar.viewmodel

import com.univ.calendar.model.EmotionItem
import com.univ.calendar.model.MemoItem
import com.univ.domain.repository.EmotionRepository

class CalendarViewModel(private val emotionRepository: EmotionRepository) {
    data class CalendarState(
        val isLoading: Boolean = true,
        val memos: List<MemoItem>? = null,
        val emotions: List<EmotionItem>? = null,
        val isError: Boolean = false,
        val updateID: Int = 0,
        val labelColorIndex: Int = -1)

    
}