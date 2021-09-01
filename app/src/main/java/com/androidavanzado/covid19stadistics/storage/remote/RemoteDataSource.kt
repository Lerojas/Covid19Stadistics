package com.androidavanzado.covid19stadistics.storage.remote

import android.util.Log
import com.androidavanzado.covid19stadistics.model.Stadistics
import com.androidavanzado.covid19stadistics.storage.DataSource
import com.androidavanzado.covid19stadistics.storage.Result
import java.util.*

class RemoteDataSource(private val apiClient: ApiClient) : DataSource {

    override suspend fun getData(date: String): Result<Stadistics> {
        val response = apiClient.build()?.getData(date)
        try{
            response?.let {

                return if(it.isSuccessful && it.body()!=null) {
                    val data = it.body()?.data
                    Result.Success(data)
                }
                else{
                    Result.Failure("Sin datos")
                }
            }?:run{
                return Result.Failure("Ocurrió un error")
            }
        }
        catch (e:Exception){
            return Result.Failure(e.message)
        }

    }

}