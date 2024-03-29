package com.zharfan.androidfundamental.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zharfan.androidfundamental.databinding.ActivityDetailTaskBackStackBinding

class DetailTaskBackStackActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailTaskBackStackBinding.inflate(layoutInflater)
    }

    private var title = ""
    private var message = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getParcelableData()
        setData()
    }

    private fun getParcelableData() {
        title = intent.getStringExtra(EXTRA_TITLE).toString()
        message = intent.getStringExtra(EXTRA_MESSAGE).toString()
    }

    private fun setData() = with(binding) {
        tvTitle.text = title
        tvMessage.text = message
    }

    companion object {
        const val EXTRA_TITLE = "com.zharfan.androidfundamental.activity.EXTRA_TITLE"
        const val EXTRA_MESSAGE = "com.zharfan.androidfundamental.activity.EXTRA_MESSAGE"
    }
}