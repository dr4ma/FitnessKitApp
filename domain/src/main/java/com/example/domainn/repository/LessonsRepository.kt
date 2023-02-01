package com.example.domainn.repository

import com.example.domainn.models.GeneralModel
import kotlinx.coroutines.flow.Flow

interface LessonsRepository {
    suspend fun getGeneralInfoLessons(): Flow<GeneralModel>
}