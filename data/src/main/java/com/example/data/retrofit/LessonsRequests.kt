package com.example.data.retrofit

import com.example.domainn.models.GeneralModel
import com.example.domainn.repository.LessonsRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class LessonsRequests : LessonsRepository {

    override suspend fun getGeneralInfoLessons(): Flow<GeneralModel> = callbackFlow {
        launch {
            val generalModel = RetrofitInstance.apiService.getGeneralInfoLessons().body()
            if (generalModel != null) {
                send(generalModel)
            }
        }
        awaitClose()
    }.distinctUntilChanged()
}