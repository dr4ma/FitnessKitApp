package com.example.data.retrofit


import android.util.Log
import com.example.data.utills.LESSON_REQUEST_TAG
import com.example.domainn.models.GeneralModel
import com.example.domainn.models.LessonModel
import com.example.domainn.models.TrainerModel
import com.example.domainn.repository.LessonsRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LessonsRequests : LessonsRepository {

    override fun getGeneralInfoLessons(): Single<GeneralModel> {
        return Single.create { subscriber ->
            val request = RetrofitInstance.apiService.getGeneralInfoLessons()
            request.enqueue(object : Callback<GeneralModel>{

                override fun onResponse(call: Call<GeneralModel>, response: Response<GeneralModel>) {
                    val generalModel = response.body()
                    if (generalModel != null) {
                        subscriber.onSuccess(generalModel)
                    }
                }

                override fun onFailure(call: Call<GeneralModel>, error: Throwable) {
                    Log.e(LESSON_REQUEST_TAG, error.message.toString())
                    subscriber.onError(error)
                }
            })
        }
    }

//    override fun getLessons(): Observable<LessonModel> {
//        var list = mutableListOf<LessonModel>()
//            val request = RetrofitInstance.apiService.getGeneralInfoLessons()
//            request.enqueue(object : Callback<GeneralModel> {
//
//                override fun onResponse(
//                    call: Call<GeneralModel>,
//                    response: Response<GeneralModel>
//                ) {
//                    val generalModel = response.body()
//                    if (generalModel != null) {
//                       list = generalModel.lessons
//                    }
//                }
//
//                override fun onFailure(call: Call<GeneralModel>, error: Throwable) {
//                    Log.e(LESSON_REQUEST_TAG, error.message.toString())
//
//                }
//            })
//        return Observable.fromIterable(list)
//    }
}