package com.androidavanzado.covid19stadistics.dependency.module

import com.androidavanzado.covid19stadistics.model.Repository
import com.androidavanzado.covid19stadistics.storage.remote.RemoteDataSource
import com.androidavanzado.covid19stadistics.storage.remote.ServicesApiInterface
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun bindRepository(servicesApiInterface: ServicesApiInterface) : Repository {
        return Repository(RemoteDataSource(servicesApiInterface))
    }
}