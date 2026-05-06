package com.univ.calendar.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univ.calendar.mapper.toDomain
import com.univ.calendar.model.EmotionItem
import com.univ.domain.repository.EmotionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor(private val emotionRepository: EmotionRepository) : ViewModel() {
    sealed class TodayIntent{
        data class DeleteEmotion(val time: Long): TodayIntent()
        data class InsertEmotion(val emotion: EmotionItem): TodayIntent()
    }

    fun handleIntent(intent: TodayIntent){
        when(intent){
            is TodayIntent.InsertEmotion -> insertEmotion(intent.emotion)
            is TodayIntent.DeleteEmotion -> deleteEmotion(intent.time)
        }
    }

    private fun insertEmotion(emotion: EmotionItem){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                emotionRepository.insertEmotion(emotion.toDomain())
            }catch(e: Exception){
                Log.d("DoraError", "error in AddEmotionViewModel insert")
            }
        }
    }

    private fun deleteEmotion(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                emotionRepository.deleteEmotion(id)
            }catch(e: Exception){
                Log.d("DoraError", "error in AddEmotionViewModel delete")
            }
        }
    }
}