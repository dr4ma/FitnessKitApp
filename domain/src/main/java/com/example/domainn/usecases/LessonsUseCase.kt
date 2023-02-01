package com.example.domainn.usecases

import com.example.domainn.models.GeneralModel
import com.example.domainn.repository.LessonsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LessonsUseCase @Inject constructor(private val lessonsRepository: LessonsRepository) {

    suspend fun getGeneralInfo(): Flow<GeneralModel> = flow {
        lessonsRepository.getGeneralInfoLessons().collect {
            emit(it)
        }
    }.flowOn(Dispatchers.IO).distinctUntilChanged()
}