package com.androidavanzado.covid19stadistics.util

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

class DateTextFormat(val monthToNameMonth : MonthToNameMonth) {

    @SuppressLint("SimpleDateFormat")
    operator fun invoke(date: Date): String {
        val dayFormat: DateFormat = SimpleDateFormat("dd")
        val day: String = dayFormat.format(date)
        val monthFormat: DateFormat = SimpleDateFormat("MM")
        val month: String = monthFormat.format(date)
        val yearFormat: DateFormat = SimpleDateFormat("yyyy")
        val year: String = yearFormat.format(date)
        val nameMonth = monthToNameMonth(month)

        return "$day de $nameMonth de $year"
    }
}