package com.androidavanzado.covid19stadistics.storage.remote

import com.androidavanzado.covid19stadistics.model.Stadistics

open class BaseResponse(val status:Int?, val msg:String?){
    companion object {
        const val STATUS_CODE:Int=200
    }

    protected fun isSuccess():Boolean {
        return status == STATUS_CODE
    }
}

class StadisticsResponse(status:Int?, val data: Stadistics?, msg:String?):
    BaseResponse(status,msg)

