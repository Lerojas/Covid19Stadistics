package com.androidavanzado.covid19stadistics.storage.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ServicesApiInterface {

    @Headers("X-RapidAPI-Key: 96afa298cbmsh913f910f914494cp110c39jsn01a32d68445e")
    @GET("/reports/total")
    suspend fun getData(@Query("date") data: String): Response<StadisticsResponse>
}