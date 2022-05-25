package com.zharfan.androidfundamental.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.zharfan.androidfundamental.R
import com.zharfan.androidfundamental.databinding.ActivityLiveDataBinding
import com.zharfan.androidfundamental.viewmodel.LiveDataViewModel

class LiveDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLiveDataBinding
    private val liveDataViewModel: LiveDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribe()
    }

    private fun subscribe() = with(binding) {
        val elapseTimeObserver = Observer<Long?> { along ->
            val newText = this@LiveDataActivity.resources.getString(R.string.tvSeconds, along)
            tvTimer.text = newText
        }

        liveDataViewModel.getElapsedTime().observe(this@LiveDataActivity,elapseTimeObserver)


    }
}