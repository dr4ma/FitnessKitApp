package com.example.domainn.models

data class GeneralModel(
    val trainers: MutableList<TrainerModel>,
    val tabs : MutableList<TabsModel>,
    val lessons: MutableList<LessonModel>
)
