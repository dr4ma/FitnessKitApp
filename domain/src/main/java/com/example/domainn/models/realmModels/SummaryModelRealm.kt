package com.example.domainn.models.realmModels

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId

open class SummaryModelRealm(
    @PrimaryKey
    var id : Int = 0,
    var lessonName : String = "",
    var place : String = "",
    var startTime : String = "",
    var endTime : String = "",
    var slots : Int = 0,
    var color : String = "",
    var fullNameTrainer : String = "",
    var day : String = "",
    var numberDay : String = "",
    var month : String = "",
    var showTittle : Boolean = false
) : RealmObject()