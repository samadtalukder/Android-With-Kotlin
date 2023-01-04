package com.samad_talukder.androidserviceexample

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_MIN
import com.samad_talukder.androidserviceexample.MainActivity.Companion.COMMAND
import com.samad_talukder.androidserviceexample.MainActivity.Companion.NOTIFICATION_CHANNEL_ID
import com.samad_talukder.androidserviceexample.MainActivity.Companion.NOTIFICATION_CHANNEL_NAME
import com.samad_talukder.androidserviceexample.MainActivity.Companion.ONGOING_NOTIFICATION_ID
import com.samad_talukder.androidserviceexample.MainActivity.Companion.START_COMMAND
import com.samad_talukder.androidserviceexample.MainActivity.Companion.STOP_COMMAND
import com.samad_talukder.androidserviceexample.MainActivity.Companion.TAG

class ForegroundService : Service() {


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand run on ${Thread.currentThread().name}")

        when (intent?.getStringExtra(COMMAND)) {
            START_COMMAND -> {
                val pendingIntent = PendingIntent.getActivity(
                    this, 0,
                    Intent(this, MainActivity::class.java),
                    0
                )

                val channelId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    createNotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME)
                } else {
                    ""
                }

                val notificationBuilder = NotificationCompat.Builder(this, channelId)

                val notification = notificationBuilder.setOngoing(true)
                    .setContentTitle("Test Foreground")
                    .setContentText("Test Our Foreground Service!")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setPriority(PRIORITY_MIN)
                    .setContentIntent(pendingIntent)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .build()


                startForeground(ONGOING_NOTIFICATION_ID, notification)
            }
            STOP_COMMAND -> stopForeground(true)
        }


        return START_NOT_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val channel =
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_NONE)
        channel.lightColor = Color.BLUE
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(channel)

        return channelId

    }

    override fun onBind(intent: Intent?): IBinder? = null
}