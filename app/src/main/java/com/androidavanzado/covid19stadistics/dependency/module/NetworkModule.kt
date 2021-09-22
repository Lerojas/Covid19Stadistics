package com.androidavanzado.covid19stadistics.dependency.module

import android.app.Application
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
}