package com.example.domainn.utils

import com.example.domainn.models.SummaryModel
import com.example.domainn.models.realmModels.SummaryModelRealm

fun SummaryModel.mapToRealm() : SummaryModelRealm{
    return SummaryModelRealm(
        id = this.id,
        lessonName = this.lessonName,
        place = this.place,
        startTime = this.startTime,
        endTime = this.endTime,
        slots = this.slots,
        color = this.color,
        fullNameTrainer = this.fullNameTrainer,
        day = this.day,
        numberDay = this.numberDay,
        month = this.month,
        showTittle = this.showTittle
    )
}

fun SummaryModelRealm.mapToLesson() : SummaryModel{
    return SummaryModel(
        id = this.id,
        lessonName = this.lessonName,
        place = this.place,
        startTime = this.startTime,
        endTime = this.endTime,
        slots = this.slots,
        color = this.color,
        fullNameTrainer = this.fullNameTrainer,
        day = this.day,
        numberDay = this.numberDay,
        month = this.month,
        showTittle = this.showTittle
    )
}