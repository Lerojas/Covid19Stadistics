package com.androidavanzado.covid19stadistics.usecase

import android.annotation.SuppressLint
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class ValidateDateSelected {

    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    operator fun invoke(stringDate: String): Boolean {

        var result = false
        val StringDateToday = dateFormat.format(Date())
        val dateToday = dateFormat.parse(StringDateToday)
        val dateSelected = dateFormat.parse(stringDate)

        if (dateSelected.compareTo(dateToday)==0) {
            result = false
        }
        else if(dateSelected.before(dateToday)){
            result = true
        }

        return result
    }
}