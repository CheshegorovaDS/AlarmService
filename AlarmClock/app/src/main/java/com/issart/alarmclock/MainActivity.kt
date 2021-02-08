package com.issart.alarmclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        choseTimeAlarm.setOnClickListener {
            choseTime()
        }
    }

    private fun choseTime() {
        val calendar = Calendar.getInstance()

        val timePickerDialog = TimePickerDialog(this, onTimeSetListener,
                calendar[Calendar.HOUR_OF_DAY],
                calendar[Calendar.MINUTE], true)
        timePickerDialog.setTitle("Выберите время")
        timePickerDialog.show()
    }

    private fun setAlarm(hour: Int, minute: Int) {
        val intent = Intent(applicationContext, AlarmReceiver::class.java)

        val manager = getSystemService(Context.ALARM_SERVICE) as? AlarmManager

        val calNow = Calendar.getInstance()
        val calSet = Calendar.getInstance()
        calSet[Calendar.HOUR_OF_DAY] = hour
        calSet[Calendar.MINUTE] = minute
        calSet[Calendar.SECOND] = 0
        calSet[Calendar.MILLISECOND] = 0
        if (calSet <= calNow) {
            calSet.add(Calendar.DATE, 1)
        }

        val pendingIntent = PendingIntent.getBroadcast(
                applicationContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT
        )

        manager?.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calSet.timeInMillis,
                TimeUnit.MINUTES.toMillis(1),
                pendingIntent
        )
    }

    // Слушатель выбора времени
    var onTimeSetListener = OnTimeSetListener { _, hour, minute ->
        setAlarm(hour, minute)
    }

}
