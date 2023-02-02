package com.example.data.utills

import android.util.Log
import io.realm.Realm

class RealmErrorCallback(private val realm: Realm) : Realm.Transaction.OnError {

    override fun onError(error: Throwable) {
        realm.close()
        Log.e(LESSON_REALM_TAG, error.message.toString())
    }
}