package com.androidavanzado.covid19stadistics.ui

import com.example.apicovidmodule.model.StadisticsApi
import java.util.*
import com.example.apicovidmodule.storage.Result

class StadisticsModelFactory {

    fun getStadisticsDataSuccess() : Result<StadisticsApi> {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DATE, -1)
        val date = calendar.time
        val stadisticsApi = StadisticsApi(date,1,1)
        val result = Result.Success(stadisticsApi)
        return result
    }

    fun getStadisticsDataFailure() : Result<Int> {

        val result = Result.Failure(3)
        return result
    }

    fun getStadisticsObjet() : StadisticsApi {

        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DATE, -1)
        val date = calendar.time

        val stadisticsApi = StadisticsApi(date,1,1)
        return stadisticsApi
    }
}