package com.technical.task.common

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkConnectionStateManager(private val connectivityManager: ConnectivityManager) {

    fun isConnected() =
        connectivityManager.run {
            getNetworkCapabilities(activeNetwork)?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            } ?: false
        }
}