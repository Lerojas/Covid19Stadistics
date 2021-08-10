package com.androidavanzado.covid19stadistics.model

import java.io.Serializable
import java.util.*

data class Stadistics(val date: Date, val confirmed : Int, val deaths: Int ): Serializable
