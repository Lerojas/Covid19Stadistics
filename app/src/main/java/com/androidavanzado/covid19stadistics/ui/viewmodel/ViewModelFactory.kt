package com.androidavanzado.covid19stadistics.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidavanzado.covid19stadistics.model.Repository

class ViewModelFactory (private val respository: Repository,
                        private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StadisticsViewModel(respository, application) as T
    }


}