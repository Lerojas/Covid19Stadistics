package com.androidavanzado.covid19stadistics.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androidavanzado.covid19stadistics.model.Repository
import com.androidavanzado.covid19stadistics.model.Stadistics
import com.androidavanzado.covid19stadistics.storage.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class StadisticsViewModel(private val repository: Repository, application: Application): AndroidViewModel(application) {

    private val _values =  MutableLiveData<Stadistics>().apply { value = null  }
    val data: LiveData<Stadistics> = _values

    private val _isViewLoading= MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError= MutableLiveData<String>()
    val onMessageError: LiveData<String> = _onMessageError

    fun getData(date: String){
        _isViewLoading.postValue(true)
        viewModelScope.launch {
            var result: Result<Stadistics> = withContext(Dispatchers.IO){
                repository.getData(date)
            }

            _isViewLoading.postValue(false)

            when(result){
                is Result.Success -> {
                    _values.value = result.data
                }
                is Result.Failure -> {
                    _onMessageError.postValue(result.msg?:"Ocurrió un error")
                }
            }
        }
    }
}