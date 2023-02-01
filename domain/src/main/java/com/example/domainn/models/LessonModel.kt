package com.example.domainn.models

data class LessonModel(
    val name : String,
    val description : String,
    val place : String,
    val coach_id : String,
    val startTime : String,
    val endTime : String,
    val date : String,
    val appointment_id : Int,
    val service_id : String,
    val available_slots : Int,
    val commercial : Boolean,
    val client_recorded : Boolean,
    val is_cancelled : Boolean,
    val tab : String,
    val color : String,
    val tab_id : String
)
