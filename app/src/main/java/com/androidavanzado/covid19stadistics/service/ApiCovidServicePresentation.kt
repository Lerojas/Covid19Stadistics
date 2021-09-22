package com.androidavanzado.covid19stadistics.service

import com.example.apicovidmodule.storage.Result
import com.example.apicovidmodule.model.StadisticsApi

interface ApiCovidServicePresentation {
    suspend fun getData(date: String) : Result<StadisticsApi>
}