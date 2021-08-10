package com.androidavanzado.covid19stadistics.storage

import com.androidavanzado.covid19stadistics.model.Stadistics
import com.androidavanzado.covid19stadistics.storage.Result
import java.util.*

interface DataSource {
    suspend fun getData(date: String) : Result<Stadistics>
}