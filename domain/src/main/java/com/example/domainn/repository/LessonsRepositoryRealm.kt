package com.example.domainn.repository

import com.example.domainn.models.GeneralModel
import com.example.domainn.models.SummaryModel
import com.example.domainn.models.realmModels.SummaryModelRealm
import kotlinx.coroutines.flow.Flow

interface LessonsRepositoryRealm {
    suspend fun insertLessons(list : MutableList<SummaryModelRealm>)
    suspend fun getLessons(function:(MutableList<SummaryModelRealm>) -> Unit)
}