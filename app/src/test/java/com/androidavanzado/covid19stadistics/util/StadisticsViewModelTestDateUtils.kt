package com.androidavanzado.covid19stadistics.util

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
class StadisticsViewModelTestDateUtils {

    private val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")

    fun dateGreaterThanToday(): String {

        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DATE, +1)
        return dateFormat.format(calendar.time)
    }

    fun dateNoData(): String {

        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.YEAR, -2)
        return dateFormat.format(calendar.time)
    }
}