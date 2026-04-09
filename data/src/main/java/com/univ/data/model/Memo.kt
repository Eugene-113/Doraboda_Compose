package com.univ.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memoTable")
data class Memo(
    @PrimaryKey val ID: Long,
    val memo: String
)
