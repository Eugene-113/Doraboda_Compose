package com.univ.doraboda_compose.di

import android.content.Context
import androidx.room.Room
import com.univ.data.db.DoraDatabase
import com.univ.data.db.emotion.EmotionDao
import com.univ.data.db.memo.MemoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun provideDoraDB(@ApplicationContext context: Context): DoraDatabase
    = Room.databaseBuilder(context, DoraDatabase::class.java, "dora_db").build()

    @Provides
    fun provideEmotionDao(database: DoraDatabase): EmotionDao
    = database.emotionDao()

    @Provides
    fun provideMemoDao(database: DoraDatabase): MemoDao
    = database.memoDao()
}