package com.androidavanzado.covid19stadistics.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androidavanzado.covid19stadistics.usecase.ValidateDateSelected
import com.example.apicovidmodule.model.RepositoryApi
import com.example.apicovidmodule.model.StadisticsApi
import com.example.apicovidmodule.storage.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

open class StadisticsViewModel @Inject constructor(
    private val repositoryApi: RepositoryApi, application: Application): AndroidViewModel(application) {

    private val validateDateSelected = ValidateDateSelected()

    val _values =  MutableLiveData<StadisticsApi>().apply { value = null  }
    val data: LiveData<StadisticsApi> = _values

    private val _onMessageError= MutableLiveData<Int>()
    val onMessageError: LiveData<Int> = _onMessageError

    fun getData(date: String){

        viewModelScope.launch(Dispatchers.IO) {

            val dateValidate = validateDateSelected(date)

            if(dateValidate) {

                var result: Result<StadisticsApi> = repositoryApi.getData(date)

                when (result) {
                    is Result.Success -> {
                        _values.postValue(result.data)
                    }
                    is Result.Failure -> {
                        _onMessageError.postValue(result.optionFailure ?: 1)
                    }
                }
            }
            else{
                _onMessageError.postValue(3)
            }
        }
    }
}