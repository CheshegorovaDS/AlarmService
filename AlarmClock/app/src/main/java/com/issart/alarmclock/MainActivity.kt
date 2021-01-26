package com.issart.alarmclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

//    private val serviceConnection = object : ServiceConnection {
//        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//
//        }
//
//        override fun onServiceDisconnected(name: ComponentName?) {
//
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAlarm()
    }

    private fun initAlarm() {
        val intent = Intent(applicationContext, AlarmService::class.java)

//        bindService(intent, serviceConnection, BIND_AUTO_CREATE)
        val manager = getSystemService(Context.ALARM_SERVICE) as? AlarmManager

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar.SECOND, 5)

        val time = calendar.timeInMillis

        val pendingIntent = PendingIntent.getService(
            applicationContext, 1, intent, 0
        )

        manager?.setRepeating(AlarmManager.RTC_WAKEUP, time, 2, pendingIntent)

//        startService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
//        unbindService(serviceConnection)

    }
}
