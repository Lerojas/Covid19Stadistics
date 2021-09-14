package com.androidavanzado.covid19stadistics.storage.remote


import com.androidavanzado.covid19stadistics.model.Stadistics
import com.androidavanzado.covid19stadistics.storage.DataSource
import com.androidavanzado.covid19stadistics.storage.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val servicesApiInterface: ServicesApiInterface) : DataSource {

    override suspend fun getData(date: String): Result<Stadistics> {
        val response = servicesApiInterface.getData(date)
        try{
            response?.let {

                return if(it.isSuccessful && it.body()!=null) {
                    val data = it.body()?.data
                    Result.Success(data)
                }
                else{
                    Result.Failure(true)
                }
            }?:run{
                return Result.Failure(false)
            }
        }
        catch (e:Exception){
            return Result.Failure(false)
        }
    }
}