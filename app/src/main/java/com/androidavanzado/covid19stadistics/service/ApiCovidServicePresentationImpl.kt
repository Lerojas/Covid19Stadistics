package com.androidavanzado.covid19stadistics.service

import au.com.carsales.basemodule.context
import com.example.apicovidmodule.model.StadisticsApi
import com.example.apicovidmodule.storage.Result
import com.example.routercovidservices.getApiCovidService
import com.example.routercovidservices.presentation.ApiCovidServicePresentation
import java.util.*

open class ApiCovidServicePresentationImpl : ApiCovidServicePresentation {

    override suspend fun getData(date: String): Result<StadisticsApi> {
        return context?.getApiCovidService()?.getDataFromApi(date)!!
    }
}