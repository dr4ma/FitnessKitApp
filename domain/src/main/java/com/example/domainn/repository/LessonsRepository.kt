package com.example.domainn.repository

import com.example.domainn.models.GeneralModel
import com.example.domainn.models.LessonModel
import com.example.domainn.models.TrainerModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface LessonsRepository {
    fun getGeneralInfoLessons(): Single<GeneralModel>
}