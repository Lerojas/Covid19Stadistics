package com.androidavanzado.covid19stadistics.dependency.module

import androidx.lifecycle.ViewModelProvider
import com.androidavanzado.covid19stadistics.ui.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class BaseViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}