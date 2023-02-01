package com.example.domainn.models

data class SummaryModel(
    val id : Int,
    val lessonName : String,
    val place : String,
    val startTime : String,
    val endTime : String,
    val slots : Int,
    val color : String,
    val fullNameTrainer : String,
    val day : String,
    val numberDay : String,
    val month : String,
    val showTittle : Boolean
)