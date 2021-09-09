package com.androidavanzado.covid19stadistics

import android.app.Application
import com.androidavanzado.covid19stadistics.dependency.component.DaggerApplicationComponent

class MyApplication : Application() {
    val appComponent = DaggerApplicationComponent.create()
}