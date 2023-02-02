package com.example.domainn.repository

import com.example.domainn.models.realmModels.SummaryModelRealm

interface LessonsRepositoryRealm {
    suspend fun insertLessonsCache(list : MutableList<SummaryModelRealm>)
    suspend fun getLessonsCache(function:(MutableList<SummaryModelRealm>) -> Unit)
}