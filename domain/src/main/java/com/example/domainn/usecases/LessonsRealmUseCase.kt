package com.example.domainn.usecases

import android.annotation.SuppressLint
import android.util.Log
import com.example.domainn.models.SummaryModel
import com.example.domainn.models.realmModels.SummaryModelRealm
import com.example.domainn.repository.LessonsRepositoryRealm
import com.example.domainn.utils.mapToLesson
import com.example.domainn.utils.mapToRealm
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

class LessonsRealmUseCase @Inject constructor(private val lessonsRepositoryRealm: LessonsRepositoryRealm) {

    private val _cache: BehaviorSubject<SummaryModel> = BehaviorSubject.create()
    val cache = _cache

    suspend fun insertLessonsCache(list: MutableList<SummaryModel>) {
        val realmModelsList = mutableListOf<SummaryModelRealm>()
        list.forEach { model ->
            realmModelsList.add(model.mapToRealm())
        }
        lessonsRepositoryRealm.insertLessonsCache(realmModelsList)
    }

    @SuppressLint("CheckResult")
    fun getLessonsCache() {
        lessonsRepositoryRealm.cache.map {
            it.mapToLesson()
        }
            .subscribeOn(Schedulers.io())
            .subscribe({
                cache.onNext(it)
            },{
                Log.e("TAG", it.message.toString())
                cache.onError(it)
            },{
                cache.onComplete()
            })
        lessonsRepositoryRealm.getLessonsCache()
    }
}