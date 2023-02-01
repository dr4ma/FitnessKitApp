package com.example.fitnesskit.di

import com.example.data.realm.LessonsRealmRequests
import com.example.data.retrofit.LessonsRequests
import com.example.domainn.repository.LessonsRepository
import com.example.domainn.repository.LessonsRepositoryRealm
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.Realm

@Module
@InstallIn(SingletonComponent::class)
class DataModel {

    @Provides
    fun provideLessonsRequests(): LessonsRepository {
        return LessonsRequests()
    }

    @Provides
    fun provideLessonsRealmRequests(realm: Realm): LessonsRepositoryRealm {
        return LessonsRealmRequests(realm = realm)
    }
}