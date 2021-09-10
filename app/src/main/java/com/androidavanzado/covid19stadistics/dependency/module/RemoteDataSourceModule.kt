package com.androidavanzado.covid19stadistics.dependency.module

import com.androidavanzado.covid19stadistics.storage.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import com.androidavanzado.covid19stadistics.storage.DataSource

@Module
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: RemoteDataSource) : DataSource
}