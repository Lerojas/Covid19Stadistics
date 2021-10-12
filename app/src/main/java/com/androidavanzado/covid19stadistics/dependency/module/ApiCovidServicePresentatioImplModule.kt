package com.androidavanzado.covid19stadistics.dependency.module

import com.androidavanzado.covid19stadistics.service.ApiCovidServicePresentationImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiCovidServicePresentationImplModule {
    @Singleton
    @Provides
    fun bindApiCovidServicePresentationImpl() : ApiCovidServicePresentationImpl {
        return ApiCovidServicePresentationImpl()
    }
}