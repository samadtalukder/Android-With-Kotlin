package com.samad_talukder.androidserviceexample

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class LocalService : Service() {
    private val localBinder = LocalBinder()

    inner class LocalBinder : Binder() {
        internal
        val getService: LocalService
            get() = this@LocalService

        fun add(a: Int, b: Int): Int {
            return a + b
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return localBinder
    }
}