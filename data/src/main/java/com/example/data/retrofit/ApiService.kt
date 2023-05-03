package com.example.data.retrofit

import com.example.domainn.models.GeneralModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("?club_id=2")
    fun getGeneralInfoLessons(): Call<GeneralModel>
}