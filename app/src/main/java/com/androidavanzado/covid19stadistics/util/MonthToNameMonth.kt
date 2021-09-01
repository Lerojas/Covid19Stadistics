package com.androidavanzado.covid19stadistics.util

class MonthToNameMonth {

    operator fun invoke(month: String) : String {

        val nameMonth = when (month) {
            "01" -> "Enero"
            "02" -> "Febrero"
            "03" -> "Marzo"
            "04" -> "Abril"
            "05" -> "Mayo"
            "06" -> "Junio"
            "07" -> "Julio"
            "08" -> "Agosto"
            "09" -> "Septiembre"
            "10" -> "Octubre"
            "11" -> "Noviembre"
            "12" -> "Diciembre"
            else -> "Invalid month"
        }

        return nameMonth
    }
}