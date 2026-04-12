package com.univ.calendar.viewmodel

import com.univ.calendar.model.EmotionItem
import com.univ.calendar.model.MemoItem
import com.univ.domain.repository.EmotionRepository
import com.univ.domain.repository.MemoRepository
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class CalendarViewModel(private val emotionRepository: EmotionRepository, private val memoRepository: MemoRepository) {
    data class CalendarState(
        val isLoading: Boolean = true,
        val memos: List<MemoItem>? = null,
        val emotions: List<EmotionItem>? = null,
        val isError: Boolean = false)
}