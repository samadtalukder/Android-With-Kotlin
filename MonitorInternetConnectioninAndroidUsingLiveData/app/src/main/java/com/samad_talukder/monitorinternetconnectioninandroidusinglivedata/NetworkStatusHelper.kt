package com.samad_talukder.monitorinternetconnectioninandroidusinglivedata

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
* ConnectivityManager - using connectivityManager for get the network connection information
* LiveData - use livedata for communicate the result to the view in sealed class format
* Coroutine - use coroutines to solve a real-time problem with android api's
* Sealed Class - creating network two states. they make it easy to handle states at the class
*/


class NetworkStatusHelper(private val context: Context) : LiveData<NetworkStatus>() {
    private val validateNetworkConnection: ArrayList<Network> = ArrayList()

    var connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private lateinit var connectivityManagerCallbacks: ConnectivityManager.NetworkCallback

    fun announceStatus() {
        if (validateNetworkConnection.isNotEmpty()) {
            postValue(NetworkStatus.Available)
        } else {
            postValue(NetworkStatus.UnAvailable)
        }
    }

    // Network connection listener
    private fun getConnectivityManagerCallBack() = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)

            val networkCapability = connectivityManager.getNetworkCapabilities(network)
            val hasNetworkConnection =
                networkCapability?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    ?: false

            if (hasNetworkConnection) {
                determineNetworkAccess(network)
            }
        }

        override fun onLost(network: Network) {
            super.onLost(network)

            validateNetworkConnection.remove(network)
            announceStatus()
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)

            if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                determineNetworkAccess(network)
            } else {
                validateNetworkConnection.remove(network)
            }

            announceStatus()
        }


    }

    private fun determineNetworkAccess(network: Network) {

        CoroutineScope(Dispatchers.IO).launch {
            if (InternetAvailability.connectionCheck()) {
                withContext(Dispatchers.Main) {
                    validateNetworkConnection.add(network)
                    announceStatus()
                }
            }
        }

    }


    // Register connectivityManager CallBack
    override fun onActive() {
        super.onActive()

        connectivityManagerCallbacks = getConnectivityManagerCallBack()
        val networkRequest =
            NetworkRequest
                .Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()
        connectivityManager.registerNetworkCallback(networkRequest, connectivityManagerCallbacks)
    }

    // UnRegister connectivityManager CallBack
    override fun onInactive() {
        super.onInactive()

        connectivityManager.unregisterNetworkCallback(connectivityManagerCallbacks)
    }
}