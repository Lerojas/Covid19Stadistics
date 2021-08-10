package com.androidavanzado.covid19stadistics.model

import com.androidavanzado.covid19stadistics.storage.DataSource
import com.androidavanzado.covid19stadistics.storage.Result
import java.util.*

class Repository (private val dataSource: DataSource){

    suspend fun getData(date: String) : Result<Stadistics>{
        return dataSource.getData(date)
    }
}