package com.zharfan.androidfundamental.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zharfan.androidfundamental.databinding.ActivityAlarmManagerBinding
import com.zharfan.androidfundamental.receiver.AlarmReceiver
import com.zharfan.androidfundamental.utils.DatePickerFragment
import com.zharfan.androidfundamental.utils.TimePickerFragment
import java.text.SimpleDateFormat
import java.util.*

class AlarmManagerActivity : AppCompatActivity(), DatePickerFragment.DialogDateListener,
    TimePickerFragment.DialogTimeListener {

    companion object {
        private const val DATE_PICKER_TAG = "DatePicker"
        private const val TIME_PICKER_ONCE_TAG = "TimePickerOnce"
        private const val TIME_PICKER_REPEAT_TAG = "TimePickerRepeat"
    }

    private val binding by lazy {
        ActivityAlarmManagerBinding.inflate(layoutInflater)
    }
    private val alarmReceiver by lazy { AlarmReceiver() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setOnceTime()
        setRepeatingTime()
        cancelRepeatingAlarm()
    }

    private fun setOnceTime() = with(binding) {
        imgBtnOnceDate.setOnClickListener {
            val datePickerFragment = DatePickerFragment()
            datePickerFragment.show(supportFragmentManager, DATE_PICKER_TAG)
        }

        imgBtnOnceTime.setOnClickListener {
            val timePickerFragmentOne = TimePickerFragment()
            timePickerFragmentOne.show(supportFragmentManager, TIME_PICKER_ONCE_TAG)
        }

        btnSetOnceAlarm.setOnClickListener {
            val onceDate = tvOnceDate.text.toString()
            val onceTime = tvOnceTime.text.toString()
            val onceMessage = etAlarmMassage.text.toString()

            alarmReceiver.setOneTimeAlarm(
                this@AlarmManagerActivity,
                AlarmReceiver.TYPE_ONE_TIME,
                onceDate,
                onceTime,
                onceMessage
            )
        }
    }

    override fun onDialogDataSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        binding.tvOnceDate.text = dateFormat.format(calendar.time)
    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.apply {
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
        }

        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        when (tag) {
            TIME_PICKER_ONCE_TAG -> binding.tvOnceTime.text = dateFormat.format(calendar.time)
            TIME_PICKER_REPEAT_TAG -> binding.tvRepeatingTime.text =
                dateFormat.format(calendar.time)
            else -> {}
        }
    }

    private fun setRepeatingTime() = with(binding) {
        imgBtnRepeatingTime.setOnClickListener {
            val timePickerFragmentRepeat = TimePickerFragment()
            timePickerFragmentRepeat.show(supportFragmentManager, TIME_PICKER_REPEAT_TAG)
        }

        btnSetRepeatingAlarm.setOnClickListener {
            val repeatTime = tvRepeatingTime.text.toString()
            val repeatMessage = etRepeatingAlarmMessage.text.toString()

            alarmReceiver.setRepeatingAlarm(
                this@AlarmManagerActivity,
                AlarmReceiver.TYPE_REPEATING,
                repeatTime,
                repeatMessage
            )
        }

    }

    private fun cancelRepeatingAlarm() = with(binding) {
        btnCancelRepeatingAlarm.setOnClickListener {
            alarmReceiver.cancelAlarm(this@AlarmManagerActivity, AlarmReceiver.TYPE_REPEATING)
        }
    }
}
