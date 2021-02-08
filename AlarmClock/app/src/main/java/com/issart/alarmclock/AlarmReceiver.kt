package com.issart.alarmclock

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class AlarmReceiver : BroadcastReceiver() {

    private val CHANNEL_ID = "alarmService"
    val notifId = 1

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Дзынь  -  дзынь", Toast.LENGTH_SHORT).show()
        Log.d("time", System.currentTimeMillis().toString())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(CHANNEL_ID, "alarm", context)
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String, context: Context) {
        val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
        )

        channel.enableVibration(false)
        channel.setSound(null, null)
        channel.lightColor = Color.BLUE
        channel.lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)

        val notificationIntent = Intent(context, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(context,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT)


        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Title")
                .setContentText("Notification text")
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
        val m = NotificationManagerCompat.from(context)
        m.notify(notifId, builder.build())

//        manager.notify(notifId, builder.build())
    }

}

