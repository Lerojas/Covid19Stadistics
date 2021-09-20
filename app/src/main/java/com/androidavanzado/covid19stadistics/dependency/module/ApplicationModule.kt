package com.androidavanzado.covid19stadistics.dependency.module

import au.com.carsales.basemodule.BaseModuleApplication
import au.com.carsales.basemodule.dependency.module.BaseApplicationModule
import dagger.Module

@Module
internal class ApplicationModule(application : BaseModuleApplication) : BaseApplicationModule(application)