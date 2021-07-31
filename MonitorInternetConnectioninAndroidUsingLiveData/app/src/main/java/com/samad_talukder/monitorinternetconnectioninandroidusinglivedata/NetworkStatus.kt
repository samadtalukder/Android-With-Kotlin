package com.samad_talukder.monitorinternetconnectioninandroidusinglivedata

sealed class NetworkStatus {
    object Available : NetworkStatus()
    object UnAvailable : NetworkStatus()
}
