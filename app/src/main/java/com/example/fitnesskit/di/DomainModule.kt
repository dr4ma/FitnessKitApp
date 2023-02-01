package com.example.fitnesskit.di

import com.example.domainn.repository.LessonsRepository
import com.example.domainn.repository.LessonsRepositoryRealm
import com.example.domainn.usecases.LessonsRealmUseCase
import com.example.domainn.usecases.LessonsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideLessonsUseCase(repository: LessonsRepository): LessonsUseCase {
        return LessonsUseCase(lessonsRepository = repository)
    }

    @Provides
    fun provideLessonsRealmUseCase(repository: LessonsRepositoryRealm): LessonsRealmUseCase {
        return LessonsRealmUseCase(lessonsRepositoryRealm = repository)
    }
}