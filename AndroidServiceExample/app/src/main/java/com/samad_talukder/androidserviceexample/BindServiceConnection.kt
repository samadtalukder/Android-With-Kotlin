package com.samad_talukder.androidserviceexample

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log

class BindServiceConnection:ServiceConnection {
    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val localServiceWithBinder: LocalService.LocalBinder =
            service as LocalService.LocalBinder

        Log.e(MainActivity.TAG, "3 + 5 = ${localServiceWithBinder.add(3, 5)}")

        Log.e(MainActivity.TAG, "getService: ${localServiceWithBinder.getService}")
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        Log.e(MainActivity.TAG, "name: $name")
    }
}