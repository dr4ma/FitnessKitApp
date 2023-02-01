package com.example.fitnesskit.utills

import com.example.domainn.models.LessonModel
import com.example.domainn.models.SummaryModel

fun LessonModel.mapToSummary( trainerName: String, tittle : Boolean) : SummaryModel{
    return SummaryModel(
        id = this.appointment_id,
        lessonName = this.name,
        place = this.place,
        startTime = this.startTime,
        endTime = this.endTime,
        slots = this.available_slots,
        color = this.color,
        fullNameTrainer = trainerName,
        day = getInfoFromDate(this.date, TypeCalendar.DAY_OF_WEEK),
        numberDay = getInfoFromDate(this.date, TypeCalendar.DAY),
        month = getInfoFromDate(this.date, TypeCalendar.MONTH),
        showTittle = tittle
    )
}