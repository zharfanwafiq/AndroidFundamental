package com.zharfan.androidfundamental.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zharfan.androidfundamental.R
import com.zharfan.androidfundamental.databinding.ActivitySmsReceiverBinding

class SmsReceiverActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySmsReceiverBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setTitles()
        setAction()
        getData()
    }

    private fun setAction() = with(binding) {
        btnClose.setOnClickListener {
            finish()
        }
    }

    private fun setTitles() {
        title = getString(R.string.incoming_message)
    }

    private fun getData() = with(binding) {
        val senderNo = intent.getStringExtra(EXTRA_SMS_NO)
        val senderMessage = intent.getStringExtra(EXTRA_SMS_MESSAGE)

        tvFrom.text = getString(R.string.from, senderNo)

        tvMessage.text = senderMessage
    }

    companion object {
        const val EXTRA_SMS_NO = "com.zharfan.androidfundamental.activity.EXTRA_SMS_NO"
        const val EXTRA_SMS_MESSAGE = "com.zharfan.androidfundamental.activity.EXTRA_SMS_MESSAGE"
    }
}