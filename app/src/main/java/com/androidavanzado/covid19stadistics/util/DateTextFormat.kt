package com.androidavanzado.covid19stadistics.util

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
class DateTextFormat {

    private val dayFormat: DateFormat = SimpleDateFormat("dd")
    private val monthFormat: DateFormat = SimpleDateFormat("MMMM")
    private val yearFormat: DateFormat = SimpleDateFormat("yyyy")

    operator fun invoke(date: Date): String {

        val day: String = dayFormat.format(date)
        val month: String = monthFormat.format(date)
        val year: String = yearFormat.format(date)
        return "$day de $month de $year"
    }
}