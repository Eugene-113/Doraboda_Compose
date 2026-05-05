package com.univ.calendar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univ.calendar.mapper.toUI
import com.univ.calendar.model.EmotionItem
import com.univ.calendar.model.MemoItem
import com.univ.domain.model.EmotionData
import com.univ.domain.model.MemoData
import com.univ.domain.repository.EmotionRepository
import com.univ.domain.repository.MemoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(private val emotionRepository: EmotionRepository, private val memoRepository: MemoRepository) : ViewModel() {
    data class CalendarState(
        val isLoading: Boolean = true,
        val memos: List<MemoItem>? = null,
        val emotions: List<EmotionItem>? = null,
        val isError: Boolean = false)
    data class DateState(val startDate: Long = -1, val endDate: Long = -1)
    sealed class CalendarIntent {
        data class LoadBetweenUserData(val date1: Long, val date2: Long): CalendarIntent()
    }
    sealed class CalendarResult{
        object Loading: CalendarResult()
        data class UserDataLoaded(val memos: List<MemoItem>?, val emotions: List<EmotionItem>?): CalendarResult()
        object Error: CalendarResult()
    }
    private val dateState = MutableStateFlow(DateState())
    val state: StateFlow<CalendarState> = dateState.filter {
        it.startDate != (-1).toLong() && it.endDate != (-1).toLong()
    }.flatMapLatest { (d1, d2) ->
        combine(memoRepository.getBetweenMemo(d1, d2),
            emotionRepository.getBetweenEmotion(d1, d2)
        ){ memos, emotions ->
            val memoItems = memos.map(MemoData::toUI)
            val emotionItems = emotions.map(EmotionData::toUI)
            CalendarResult.UserDataLoaded(memoItems, emotionItems)
        }.map { result ->
            reduce(result, state.value)
        }.onStart {
            emit(reduce(CalendarResult.Loading, state.value))
        }
    }.catch {
        emit(reduce(CalendarResult.Error, state.value))
    }.stateIn(scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = CalendarState()
    )

    private fun reduce(result: CalendarResult, thisState: CalendarState): CalendarState{
        return when(result){
            is CalendarResult.Loading -> thisState.copy(isLoading = true, isError = false)
            is CalendarResult.UserDataLoaded -> thisState.copy(isLoading = false, memos = result.memos, emotions = result.emotions, isError = false)
            is CalendarResult.Error -> thisState.copy(isLoading = false, isError = true)
        }
    }

    fun handleIntent(intent: CalendarIntent){
        when(intent){
            is CalendarIntent.LoadBetweenUserData -> dateState.value =
                dateState.value.copy(
                startDate = intent.date1,
                endDate = intent.date2
            )
        }
    }
}