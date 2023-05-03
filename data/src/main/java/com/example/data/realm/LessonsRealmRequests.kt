package com.example.data.realm

import com.example.data.utills.RealmErrorCallback
import com.example.domainn.models.realmModels.SummaryModelRealm
import com.example.domainn.repository.LessonsRepositoryRealm
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.realm.Realm

class LessonsRealmRequests(private val realm: Realm) : LessonsRepositoryRealm {

    override val cache: BehaviorSubject<SummaryModelRealm> by lazy {
        BehaviorSubject.create()
    }
    override suspend fun insertLessonsCache(list: MutableList<SummaryModelRealm>) {
        realm.executeTransactionAsync({ transition ->
            transition.deleteAll()
            transition.insert(list)
        }, RealmErrorCallback(realm))
    }

    override fun getLessonsCache() {
        realm.executeTransactionAsync({ transition ->
            transition.where(SummaryModelRealm::class.java).findAll().forEach {
                cache.onNext(it)
            }
            cache.onComplete()
        }, RealmErrorCallback(realm))
    }
}