package com.androidavanzado.covid19stadistics.util

import android.annotation.SuppressLint
import android.util.Log
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class DateTextFormat {

    @SuppressLint("SimpleDateFormat")
    operator fun invoke(date: Date): String {
        val dayFormat: DateFormat = SimpleDateFormat("dd")
        val day: String = dayFormat.format(date)
        val monthFormat: DateFormat = SimpleDateFormat("MMMM")
        val month: String = monthFormat.format(date)
        val yearFormat: DateFormat = SimpleDateFormat("yyyy")
        val year: String = yearFormat.format(date)

        return "$day de $month de $year"
    }
}