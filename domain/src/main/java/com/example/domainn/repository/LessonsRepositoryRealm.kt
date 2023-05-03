package com.example.domainn.repository

import com.example.domainn.models.SummaryModel
import com.example.domainn.models.realmModels.SummaryModelRealm
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

interface LessonsRepositoryRealm {

    val cache : BehaviorSubject<SummaryModelRealm>
    suspend fun insertLessonsCache(list : MutableList<SummaryModelRealm>)
    fun getLessonsCache()
}