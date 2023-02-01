package com.example.fitnesskit.utills

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Toast
import com.example.fitnesskit.R
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

//fun String.decode() = replace("\\\\u([0-9A-Fa-f]{4})".toRegex()) {
//    String(Character.toChars(it.groupValues[1].toInt(radix = 16)))
//}

fun String.toDate(): Date? {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.parse(this)
}

fun showToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun getInfoFromDate(
    dateStr: String,
    type : TypeCalendar
) : String{
    val calendar = Calendar.getInstance(Locale.getDefault())
    val date = dateStr.toDate()
    if (date != null) {
        calendar.time = date
    }

    return when(type){
        TypeCalendar.DAY -> {
            getDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH))
        }
        TypeCalendar.DAY_OF_WEEK -> {
            getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK))
        }
        else -> {
            getMonth(calendar.get(Calendar.MONTH))
        }
    }
}

fun getDayOfWeek(day: Int): String {
    return when (day) {
        1 -> SUNDAY
        2 -> MONDAY
        3 -> TUESDAY
        4 -> WEDNESDAY
        5 -> THURSDAY
        6 -> FRIDAY
        else -> SATURDAY
    }
}

fun getDayOfMonth(day: Int): String {
    return if (day <= 9) {
        "0$day"
    } else {
        day.toString()
    }
}

fun getMonth(month: Int): String {
    return when (month) {
        1 -> FEBRUARY
        2 -> MARCH
        3 -> APRIL
        4 -> MAY
        5 -> JUNE
        6 -> JULY
        7 -> AUGUST
        8 -> SEPTEMBER
        9 -> OCTOBER
        10 -> NOVEMBER
        11 -> DECEMBER
        else -> JANUARY
    }
}