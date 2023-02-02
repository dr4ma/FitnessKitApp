package com.example.data.realm

import android.util.Log
import com.example.data.utills.RealmErrorCallback
import com.example.domainn.models.realmModels.SummaryModelRealm
import com.example.domainn.repository.LessonsRepositoryRealm
import io.realm.Realm

class LessonsRealmRequests(private val realm: Realm) : LessonsRepositoryRealm {

    override suspend fun insertLessonsCache(list: MutableList<SummaryModelRealm>) {
        realm.executeTransactionAsync({ transition ->
            transition.insertOrUpdate(list)
        }, RealmErrorCallback(realm))
    }

    override suspend fun getLessonsCache(function: (MutableList<SummaryModelRealm>) -> Unit) {
        realm.executeTransactionAsync({ transition ->
            function(transition.where(SummaryModelRealm::class.java).findAll())
        }, RealmErrorCallback(realm))
    }
}