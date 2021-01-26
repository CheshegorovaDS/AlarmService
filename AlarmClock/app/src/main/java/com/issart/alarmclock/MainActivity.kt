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
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        choseTimeAlarm.setOnClickListener {
            choseTime()//пока просто пусть звенит через пять секунд после нажатия
        }
    }

    private fun choseTime() {
        //open chose time
        val time = System.currentTimeMillis() + 5000

        setAlarm(time)
    }

    private fun setAlarm(time: Long) {
        val intent = Intent(applicationContext, AlarmReceiver::class.java)

        val manager = getSystemService(Context.ALARM_SERVICE) as? AlarmManager

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time

        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext, 1, intent, 0
        )

        manager?.set(AlarmManager.RTC_WAKEUP, time, pendingIntent)
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}
