package com.androidavanzado.covid19stadistics

import au.com.carsales.basemodule.router.BaseModuleLifeCycleManager

internal class ModuleLifeCycleManager (application: MyApplication) :
    BaseModuleLifeCycleManager(application){

        init {
            moduleLifeCycleList.add(GlobalModuleLifeCycle(application))
        }

}