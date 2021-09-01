package com.androidavanzado.covid19stadistics.util

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@SuppressLint("SimpleDateFormat")
class DateYesterday {

    private val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")

    operator fun invoke(): String {

        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DATE, -1)
        return dateFormat.format(calendar.time)
    }
}