package com.example.data.retrofit

import android.util.Log
import com.example.data.utills.LESSON_REQUEST_TAG
import com.example.domainn.models.GeneralModel
import com.example.domainn.repository.LessonsRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class LessonsRequests : LessonsRepository {

    override suspend fun getGeneralInfoLessons(): Flow<GeneralModel> = callbackFlow {
        val request = RetrofitInstance.apiService.getGeneralInfoLessons()
        if(request.isSuccessful){
            launch {
                val generalModel = request.body()
                if (generalModel != null) {
                    send(generalModel)
                }
            }
        }
        else{
            Log.e(LESSON_REQUEST_TAG, request.code().toString())
        }
        awaitClose()
    }.distinctUntilChanged()
}