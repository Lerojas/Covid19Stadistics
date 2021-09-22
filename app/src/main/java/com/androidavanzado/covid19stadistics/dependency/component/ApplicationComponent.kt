package com.androidavanzado.covid19stadistics.dependency.component

import au.com.carsales.basemodule.dependency.component.BaseApplicationComponent
import com.androidavanzado.covid19stadistics.MyApplication
import com.androidavanzado.covid19stadistics.dependency.module.*
import com.androidavanzado.covid19stadistics.ui.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        ApiCovidServicePresentationImplModule::class
    ]
)

interface ApplicationComponent : BaseApplicationComponent {
    fun inject(application: MyApplication)
    fun inject(activity: HomeActivity)
}