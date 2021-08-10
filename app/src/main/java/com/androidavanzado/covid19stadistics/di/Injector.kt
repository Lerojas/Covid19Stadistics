package com.androidavanzado.covid19stadistics.di

import com.androidavanzado.covid19stadistics.model.Repository
import com.androidavanzado.covid19stadistics.storage.DataSource
import com.androidavanzado.covid19stadistics.storage.remote.ApiClient
import com.androidavanzado.covid19stadistics.storage.remote.RemoteDataSource

object Injector {

    private val dataSource: DataSource = RemoteDataSource(ApiClient.getInstance())
    private val noteRepository: Repository =
        Repository((dataSource))

    fun provideRepository() = noteRepository
}