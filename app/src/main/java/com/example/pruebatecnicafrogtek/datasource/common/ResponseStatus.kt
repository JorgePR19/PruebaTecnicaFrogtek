package com.example.pruebatecnicaliverpool.data.utilsresponse

sealed class ResponseStatus<T> {
    class Success<T>(val data: T) : ResponseStatus<T>()
    class Loading<T> : ResponseStatus<T>()
    class Error<T>(val message: Int) : ResponseStatus<T>()
}