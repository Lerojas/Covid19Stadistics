package com.androidavanzado.covid19stadistics.usecase

import java.util.*

class ValidateDateSelected {

    operator fun invoke(day: Int, month: Int, year: Int) : Boolean {

        val calendar = Calendar.getInstance()
        calendar.set(year,month,day)
        val dateSelected = calendar.time

        return dateSelected.before(Date())
    }
}