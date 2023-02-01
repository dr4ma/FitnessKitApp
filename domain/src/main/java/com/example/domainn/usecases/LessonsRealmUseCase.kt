package com.example.domainn.usecases

import com.example.domainn.models.SummaryModel
import com.example.domainn.models.realmModels.SummaryModelRealm
import com.example.domainn.repository.LessonsRepositoryRealm
import com.example.domainn.utils.mapToLesson
import com.example.domainn.utils.mapToRealm
import javax.inject.Inject

class LessonsRealmUseCase @Inject constructor(private val lessonsRepositoryRealm: LessonsRepositoryRealm) {

    suspend fun insertLessons(list : MutableList<SummaryModel>){
        val realmModelsList = mutableListOf<SummaryModelRealm>()
        list.forEach{ model ->
            realmModelsList.add(model.mapToRealm())
        }
        lessonsRepositoryRealm.insertLessons(realmModelsList)
    }

    suspend fun getLessons(function:(MutableList<SummaryModel>) -> Unit){
        val lessonModelsList = mutableListOf<SummaryModel>()
        lessonsRepositoryRealm.getLessons{
            it.forEach{ modelRealm ->
                lessonModelsList.add(modelRealm.mapToLesson())
            }
        }
        function(lessonModelsList)
    }
}