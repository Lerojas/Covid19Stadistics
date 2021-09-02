package com.androidavanzado.covid19stadistics.usecase

import android.util.Log
import java.util.Calendar
import java.util.Date

class ValidateDateSelected {

    operator fun invoke(day: Int, month: Int, year: Int) : Boolean {

        val calendar = Calendar.getInstance()
        calendar.set(year,month,day)
        val dateSelected = calendar.time
        return dateSelected.before(Date())
    }
}