package com.androidavanzado.covid19stadistics.util

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class DateYesterday {

    @SuppressLint("SimpleDateFormat")
    operator fun invoke(): String {

        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DATE, -1)

        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        return df.format(calendar.time)
    }
}