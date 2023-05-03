package com.example.fitnesskit.presentation.fragments.lessons

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domainn.models.GeneralModel
import com.example.domainn.models.LessonModel
import com.example.domainn.models.SummaryModel
import com.example.domainn.usecases.LessonsRealmUseCase
import com.example.domainn.usecases.LessonsUseCase
import com.example.fitnesskit.utills.GET_LESSONS_CACHE_TAG
import com.example.fitnesskit.utills.GET_SUMMARY_INFO_TAG
import com.example.fitnesskit.utills.mapToSummary
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonsViewModel @Inject constructor(
    private val lessonsUseCase: LessonsUseCase,
    private val lessonsRealmUseCase: LessonsRealmUseCase
) : ViewModel() {

    private val _summaryLessons: MutableLiveData<MutableList<SummaryModel>> = MutableLiveData()
    val summaryLessons = _summaryLessons
    private val disposeBag = CompositeDisposable()

    fun getSummaryInfoLessons() {
        val generalInfo = lessonsUseCase.getGeneralInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                _summaryLessons.postValue(createSummaryList(it))
            }, {
                Log.e(GET_SUMMARY_INFO_TAG, it.message.toString())
                _summaryLessons.postValue(mutableListOf())
            })
        disposeBag.add(generalInfo)
    }

    private fun createSummaryList(generalModel: GeneralModel): MutableList<SummaryModel> {
        val summaryList = mutableListOf<SummaryModel>()
        var showTittle: Boolean
        var oldDate = ""
        val generalList = generalModel.lessons.sortedWith(compareBy<LessonModel> { it.date }.thenBy { it.startTime })
        generalList.forEach { lessonModel ->

            if (oldDate != lessonModel.date) {
                showTittle = true
                oldDate = lessonModel.date
            } else {
                showTittle = false
            }

            if (lessonModel.coach_id == "") {
                summaryList.add(lessonModel.mapToSummary(lessonModel.coach_id, showTittle))
            } else {
                run trainerLoop@{
                    generalModel.trainers.forEach { trainerModel ->
                        if (trainerModel.id == lessonModel.coach_id) {
                            summaryList.add(
                                lessonModel.mapToSummary(
                                    trainerModel.full_name,
                                    showTittle
                                )
                            )
                            return@trainerLoop
                        }
                    }
                }
            }
        }
        insertLessonsCache(summaryList)
        return summaryList
    }

    private fun insertLessonsCache(list: MutableList<SummaryModel>) {
        viewModelScope.launch {
            lessonsRealmUseCase.insertLessonsCache(list)
        }
    }

    fun getLessonsCache() {
        val list = mutableListOf<SummaryModel>()
        val cache = lessonsRealmUseCase.cache
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                list.add(it)
            }, {
                Log.e(GET_LESSONS_CACHE_TAG, it.message.toString())
                _summaryLessons.postValue(mutableListOf())
            }, {
                _summaryLessons.postValue(list)
            })
        lessonsRealmUseCase.getLessonsCache()
        disposeBag.add(cache)
    }

    fun clearDisposeBag(){
        disposeBag.clear()
    }
}