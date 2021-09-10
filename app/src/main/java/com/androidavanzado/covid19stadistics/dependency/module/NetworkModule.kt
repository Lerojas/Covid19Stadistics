package com.androidavanzado.covid19stadistics.dependency.module

import android.app.Application
import com.androidavanzado.covid19stadistics.storage.remote.ServicesApiInterface
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideApplication() : Application{
        return Application()
    }

    @Singleton
    @Provides
    @Named("provideOkHttpClient")
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    @Named("provideRetrofit")
    fun provideRetrofit(@Named("provideOkHttpClient") okHttpClient: OkHttpClient): Retrofit {

        val API_BASE_URL = "https://covid-19-statistics.p.rapidapi.com"
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        return builder.client(okHttpClient).build()
    }

    @Provides
    fun provideServicesInterface(@Named("provideRetrofit") retrofit: Retrofit): ServicesApiInterface {
        return retrofit.create(ServicesApiInterface::class.java)
    }
}