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
    sealed class AddEmotionIntent{
        data class DeleteEmotion(val time: Long): AddEmotionIntent()
        data class InsertEmotion(val emotion: EmotionItem): AddEmotionIntent()
    }

    fun handleIntent(intent: AddEmotionIntent){
        when(intent){
            is AddEmotionIntent.InsertEmotion -> insertEmotion(intent.emotion)
            is AddEmotionIntent.DeleteEmotion -> deleteEmotion(intent.time)
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