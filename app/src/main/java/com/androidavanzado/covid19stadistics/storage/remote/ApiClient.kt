package com.androidavanzado.covid19stadistics.storage.remote

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.*

object ApiClient {

    private val API_BASE_URL = "https://covid-19-statistics.p.rapidapi.com"
    private var servicesApiInterface: ServicesApiInterface?=null

    fun build() :ServicesApiInterface?{
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()

        var retrofit : Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(
            ServicesApiInterface::class.java)

        return servicesApiInterface as ServicesApiInterface
    }

    fun getInstance() = this

    interface ServicesApiInterface{

        @Headers("X-RapidAPI-Key: 96afa298cbmsh913f910f914494cp110c39jsn01a32d68445e")
        @GET("/reports/total")
        suspend fun getData(@Query ("date") data: String): Response<StadisticsResponse>
    }

}