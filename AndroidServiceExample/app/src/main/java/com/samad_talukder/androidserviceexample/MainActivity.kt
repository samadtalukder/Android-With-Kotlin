package com.samad_talukder.androidserviceexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.samad_talukder.androidserviceexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "SERVICE"
        const val COMMAND = "command"
        const val NOTIFICATION = "NOTIFICATION"
        const val STATE = "STATE"
        const val NOTIFICATION_CHANNEL_ID = "MyNotificationChannel"
        const val NOTIFICATION_CHANNEL_NAME = "My Background Service"
        const val ONGOING_NOTIFICATION_ID = 1
        const val START_COMMAND = "start_command"
        const val STOP_COMMAND = "stop_command"
    }

    private val serviceConnection = BindServiceConnection()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var isBind = false
    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.getStringExtra(STATE)?.let {
                Log.e(TAG, "onReceive: $it")
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnBind.setOnClickListener {

            bindService(
                Intent(this, LocalService::class.java),
                serviceConnection,
                Context.BIND_AUTO_CREATE
            )

            isBind = true
            Log.e(TAG, "isServiceBind: $isBind")
        }

        binding.btnUnbind.setOnClickListener {
            if (isBind) {
                unbindService(serviceConnection)
                isBind = false
                Log.e(TAG, "isServiceBind: $isBind")
            }
        }

        binding.btnStartBasicService.setOnClickListener {
            startService(Intent(this, BasicService::class.java))
        }

        binding.btnStopBasicService.setOnClickListener {
            stopService(Intent(this, BasicService::class.java))
        }

        binding.btnStopServiceItSelf.setOnClickListener {
            startService(Intent(this, BasicService::class.java).also {
                it.putExtra(START_COMMAND, STOP_COMMAND)
            })
        }

        binding.btnStartForeground.setOnClickListener {
            startService(Intent(this, ForegroundService::class.java).also {
                it.putExtra(COMMAND, START_COMMAND)
            })
        }

        binding.btnStopForeground.setOnClickListener {
            stopService(Intent(this, ForegroundService::class.java).also {
                it.putExtra(COMMAND, STOP_COMMAND)
            })
        }

        binding.btnBackground.setOnClickListener {
            startService(Intent(this, BackgroundService::class.java))
        }

    }

    override fun onResume() {
        super.onResume()

        registerReceiver(broadcastReceiver, IntentFilter(NOTIFICATION))
    }

    override fun onPause() {
        super.onPause()

        unregisterReceiver(broadcastReceiver)
        unbindService(serviceConnection)
    }
}