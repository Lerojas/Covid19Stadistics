package com.androidavanzado.covid19stadistics.storage

sealed class Result <out T> {
    data class Success<T>(val data : T?) : Result<T>()
    data class Failure(val optionFailure : Boolean?) : Result<Nothing>()
}