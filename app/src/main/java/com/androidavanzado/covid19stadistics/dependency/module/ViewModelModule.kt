package com.androidavanzado.covid19stadistics.dependency.module

import androidx.lifecycle.ViewModel
import com.androidavanzado.covid19stadistics.ui.viewmodel.StadisticsViewModel
import com.androidavanzado.covid19stadistics.ui.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule : BaseViewModelModule(){
    @Binds
    @IntoMap
    @ViewModelKey(StadisticsViewModel::class)
    abstract fun bindViewModel(viewModel: StadisticsViewModel) : ViewModel
}