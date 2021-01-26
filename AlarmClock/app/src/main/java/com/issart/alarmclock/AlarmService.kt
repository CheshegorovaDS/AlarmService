package com.issart.alarmclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import java.util.*

class AlarmService : Service() {

    private val serviceBinder: Binder = ServiceBinder()

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent): IBinder {
        return serviceBinder
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroyService", Toast.LENGTH_SHORT).show()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        Toast.makeText(this, "ServiceClass.onStart()", Toast.LENGTH_LONG).show()
        intent?.let { createAlarm(it) }
    }

    private fun createAlarm(intent: Intent) {
        Toast.makeText(applicationContext, "Дзынь  -  дзынь", Toast.LENGTH_SHORT).show()
    }

    inner class ServiceBinder : Binder() {
        fun getService(): AlarmService {
            return this@AlarmService
        }
    }

    companion object {
        const val START_ALARM_CODE = 1
        const val END_ALARM_CODE = 1
    }
}