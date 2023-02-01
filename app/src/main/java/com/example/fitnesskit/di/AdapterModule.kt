package com.example.fitnesskit.di

import com.example.fitnesskit.presentation.fragments.lessons.adapter.LessonAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    @Singleton
    fun provideLessonAdapter(): LessonAdapter {
        return LessonAdapter()
    }
}