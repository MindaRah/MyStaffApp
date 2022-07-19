package com.example.mypeopleandroomapp.utils

sealed class ResponseStatus {
    class SUCCESS<T>(val response: T): ResponseStatus()
    object LOADING: ResponseStatus()
    class ERROR(val error: Exception): ResponseStatus()
}