package com.androidavanzado.covid19stadistics.dependency.component

import com.androidavanzado.covid19stadistics.dependency.module.*
import com.androidavanzado.covid19stadistics.ui.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        RepositoryModule::class,
        RemoteDataSourceModule::class,
        NetworkModule::class
    ]
)

interface ApplicationComponent {
    fun inject(activity: HomeActivity)
}