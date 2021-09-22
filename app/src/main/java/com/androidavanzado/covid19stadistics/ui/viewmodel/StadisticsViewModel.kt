package com.androidavanzado.covid19stadistics.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androidavanzado.covid19stadistics.service.ApiCovidServicePresentationImpl
import com.example.apicovidmodule.model.RepositoryApi
import com.example.apicovidmodule.model.StadisticsApi
import com.example.apicovidmodule.storage.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class StadisticsViewModel @Inject constructor(
    private val repositoryApi: RepositoryApi, application: Application): AndroidViewModel(application) {

    private val _values =  MutableLiveData<StadisticsApi>().apply { value = null  }
    val data: LiveData<StadisticsApi> = _values

    private val _onMessageError= MutableLiveData<Boolean>()
    val onMessageError: LiveData<Boolean> = _onMessageError

    fun getData(date: String){
        viewModelScope.launch {

            var result: Result<StadisticsApi> = withContext(Dispatchers.IO){
                repositoryApi.getData(date)
            }

            when(result){
                is Result.Success -> {
                    _values.value = result.data
                }
                is Result.Failure -> {
                    _onMessageError.postValue(result.optionFailure?:false)
                }
            }
        }
    }
}