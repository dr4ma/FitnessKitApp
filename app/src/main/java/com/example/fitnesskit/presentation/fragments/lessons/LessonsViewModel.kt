package com.example.fitnesskit.presentation.fragments.lessons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domainn.models.GeneralModel
import com.example.domainn.models.LessonModel
import com.example.domainn.models.SummaryModel
import com.example.domainn.usecases.LessonsRealmUseCase
import com.example.domainn.usecases.LessonsUseCase
import com.example.fitnesskit.utills.mapToSummary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonsViewModel @Inject constructor(
    private val lessonsUseCase: LessonsUseCase,
    private val lessonsRealmUseCase: LessonsRealmUseCase
) : ViewModel() {

    private val _summaryLessons: MutableLiveData<MutableList<SummaryModel>> = MutableLiveData()
    val summaryLessons = _summaryLessons

    fun getSummaryInfoLessons() {
        viewModelScope.launch {
            lessonsUseCase.getGeneralInfo().collect { generalModel ->
                _summaryLessons.postValue(createSummaryList(generalModel))
            }
        }
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
                            summaryList.add(lessonModel.mapToSummary(trainerModel.full_name, showTittle))
                            return@trainerLoop
                        }
                    }
                }
            }
        }
        insertLessonsCache(summaryList)
        return summaryList
    }

    private fun insertLessonsCache(list : MutableList<SummaryModel>) {
        viewModelScope.launch {
            lessonsRealmUseCase.insertLessonsCache(list)
        }
    }

    fun getLessonsCache(){
        viewModelScope.launch {
            lessonsRealmUseCase.getLessonsCache{
                _summaryLessons.postValue(it)
            }
        }
    }
}