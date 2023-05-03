package com.example.domainn.usecases

import com.example.domainn.models.GeneralModel
import com.example.domainn.repository.LessonsRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class LessonsUseCase @Inject constructor(private val lessonsRepository: LessonsRepository) {

    fun getGeneralInfo(): Single<GeneralModel> {
        return lessonsRepository.getGeneralInfoLessons()
            .observeOn(Schedulers.io())
    }
}