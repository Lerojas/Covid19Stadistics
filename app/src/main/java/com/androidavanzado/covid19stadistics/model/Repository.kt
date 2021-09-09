package com.androidavanzado.covid19stadistics.model

import com.androidavanzado.covid19stadistics.storage.DataSource
import com.androidavanzado.covid19stadistics.storage.Result
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val dataSource: @JvmSuppressWildcards DataSource){

    suspend fun getData(date: String) : Result<Stadistics>{
        return dataSource.getData(date)
    }
}