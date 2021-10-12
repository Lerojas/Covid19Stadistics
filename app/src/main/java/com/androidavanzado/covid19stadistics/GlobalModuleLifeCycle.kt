package com.androidavanzado.covid19stadistics

import au.com.carsales.basemodule.BaseModuleApplication
import au.com.carsales.basemodule.router.BaseModuleLifeCycle
import au.com.carsales.basemodule.router.IModuleConfig

class GlobalModuleLifeCycle (application: MyApplication) : BaseModuleLifeCycle(application) {

    override fun onCreate(config: IModuleConfig) {
        super.onCreate(config)

    }
}