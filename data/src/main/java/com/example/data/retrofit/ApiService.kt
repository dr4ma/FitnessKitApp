package com.example.data.retrofit

import com.example.domainn.models.GeneralModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("?club_id=2")
    suspend fun getGeneralInfoLessons(): Response<GeneralModel>
}