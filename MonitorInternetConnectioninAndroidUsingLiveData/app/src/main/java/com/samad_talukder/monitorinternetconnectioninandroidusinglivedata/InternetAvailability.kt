package com.samad_talukder.monitorinternetconnectioninandroidusinglivedata

import java.lang.Exception
import java.net.InetSocketAddress
import java.net.Socket

object InternetAvailability {
    fun connectionCheck():Boolean{
        return try {
            val socket = Socket()
            socket.connect(InetSocketAddress("192.168.0.2",1993))
            socket.close()
            true

        }catch (e:Exception){
            e.printStackTrace()
            false
        }
    }
}