package com.androidavanzado.covid19stadistics

import au.com.carsales.basemodule.router.BaseModuleLifeCycleManager
import com.example.routercovidservices.ApiCovid.GlobalApiCovidRouterServiceLifeCycle

internal class ModuleLifeCycleManager (application: MyApplication) :
    BaseModuleLifeCycleManager(application){

        init {
            moduleLifeCycleList.add(GlobalModuleLifeCycle(application))
            moduleLifeCycleList.add(GlobalApiCovidRouterServiceLifeCycle(application))
        }

}