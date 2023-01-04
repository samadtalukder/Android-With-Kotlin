package com.samad_talukder.androidserviceexample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.samad_talukder.androidserviceexample.MainActivity.Companion.START_COMMAND
import com.samad_talukder.androidserviceexample.MainActivity.Companion.STOP_COMMAND
import com.samad_talukder.androidserviceexample.MainActivity.Companion.TAG

class BasicService : Service() {

    override fun onCreate() {
        super.onCreate()

        Log.e(TAG, "Service onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand run on ${Thread.currentThread().name}")

        intent?.getStringExtra(START_COMMAND)?.takeIf { it == STOP_COMMAND }?.apply { stopSelf() }

        return START_NOT_STICKY

    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "Service onDestroy")
    }
}