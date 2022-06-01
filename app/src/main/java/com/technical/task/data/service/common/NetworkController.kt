package com.technical.task.data.service.common

import com.technical.task.common.EMPTY_STRING
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NetworkController {

    suspend fun <T>performNetworkRequest(call: Call<T>): ServiceAction<T> {
        return call.callRequest()
    }

    private suspend fun <T> Call<T>.callRequest(): ServiceAction<T> {
        return suspendCoroutine { continuation ->
            val retrofitCallback = object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    continuation.resume(ServiceAction.Success(response.body()))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resume(handleError(t))
                }
            }
            enqueue(retrofitCallback)
        }
    }

    private fun <T> handleError(throwable: Throwable): ServiceAction<T> {
        return when (throwable) {
            is IOException -> ServiceAction.NetworkError
            is SocketTimeoutException -> ServiceAction.TimeoutError
            else -> ServiceAction.GeneralError(errorMessage = throwable.message ?: EMPTY_STRING)
        }
    }
}