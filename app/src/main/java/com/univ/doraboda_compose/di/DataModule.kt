package com.univ.doraboda_compose.di

import com.univ.data.db.emotion.EmotionDataSource
import com.univ.data.db.emotion.EmotionDataSourceImpl
import com.univ.data.db.memo.MemoDataSource
import com.univ.data.db.memo.MemoDataSourceImpl
import com.univ.data.repository.EmotionRepositoryImpl
import com.univ.data.repository.MemoRepositoryImpl
import com.univ.domain.repository.EmotionRepository
import com.univ.domain.repository.MemoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsEmotionDataSource(
        emotionDataSourceImpl: EmotionDataSourceImpl
    ): EmotionDataSource

    @Binds
    abstract fun bindsMemoDataSource(
        memoDataSourceImpl: MemoDataSourceImpl
    ): MemoDataSource

    @Binds
    abstract fun bindsEmotionRepository(
        emotionRepositoryImpl: EmotionRepositoryImpl
    ): EmotionRepository

    @Binds
    abstract fun bindsMemoRepository(
        memoRepositoryImpl: MemoRepositoryImpl
    ): MemoRepository
}