package com.technical.task.data.service.common

sealed class ServiceAction<out T> {
    data class Success<out T: Any>(val data: T?) : ServiceAction<T>()
    data class GeneralError(val errorMessage: String) : ServiceAction<Nothing>()
    object TimeoutError : ServiceAction<Nothing>()
    object NetworkError : ServiceAction<Nothing>()
}