package com.example.fitnesskit.di

import android.content.Context
import com.example.fitnesskit.utills.CheckConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtilModule {

    @Provides
    @Singleton
    fun provideCheckConnection(@ApplicationContext context: Context): CheckConnection {
        return CheckConnection(context = context)
    }
}