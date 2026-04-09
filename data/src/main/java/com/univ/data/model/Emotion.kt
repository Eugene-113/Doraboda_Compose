package com.univ.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emotionTable")
data class Emotion(
    @PrimaryKey val ID: Long,
    val emotion: String
)