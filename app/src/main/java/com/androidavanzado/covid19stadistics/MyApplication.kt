package com.androidavanzado.covid19stadistics

import android.content.Context
import au.com.carsales.basemodule.BaseModuleApplication
import au.com.carsales.basemodule.router.BaseModuleLifeCycleManager
import com.androidavanzado.covid19stadistics.MyApplication.Companion.applicationComponent
import com.androidavanzado.covid19stadistics.dependency.component.ApplicationComponent
import com.androidavanzado.covid19stadistics.dependency.component.DaggerApplicationComponent
import com.androidavanzado.covid19stadistics.dependency.module.ApplicationModule

class MyApplication : BaseModuleApplication(){

    companion object {
        var applicationComponent: ApplicationComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        appComponent().inject(this)
    }

    override fun initLifeCycleManager(): BaseModuleLifeCycleManager {
        return ModuleLifeCycleManager(this)
    }

}

fun Context.appComponent(): ApplicationComponent {
    return buildDagger(this.applicationContext)
}

private fun buildDagger(context: Context): ApplicationComponent {
    if (applicationComponent == null) {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .build()
    }
    return applicationComponent!!
}
