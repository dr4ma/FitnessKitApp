package com.example.data.realm

import com.example.domainn.models.realmModels.SummaryModelRealm
import com.example.domainn.repository.LessonsRepositoryRealm
import io.realm.Realm

class LessonsRealmRequests(private val realm: Realm) : LessonsRepositoryRealm {

    override suspend fun insertLessons(list: MutableList<SummaryModelRealm>) {
        realm.executeTransactionAsync { transaction ->
            transaction.insertOrUpdate(list)
        }
    }

    override suspend fun getLessons(function: (MutableList<SummaryModelRealm>) -> Unit) {
        realm.executeTransactionAsync { transition ->
            function(transition.where(SummaryModelRealm::class.java).findAll())
        }
    }
}