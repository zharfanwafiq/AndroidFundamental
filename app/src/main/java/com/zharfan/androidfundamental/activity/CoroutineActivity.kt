package com.zharfan.androidfundamental.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.zharfan.androidfundamental.R
import com.zharfan.androidfundamental.databinding.ActivityCoroutineBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoroutineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAction()
    }

    private fun setAction(){
        binding.apply {
            btnStart.setOnClickListener {
                lifecycleScope.launch(Dispatchers.Default){
                    for (i in 1..10){
                        delay(100)
                        val percentage = i * 10
                        withContext(Dispatchers.Main){
                            if(percentage == 100){
                                tvStatus.setText(R.string.task_completed)
                            }else{
                                tvStatus.text = String.format(getString(R.string.compressing),percentage)
                            }
                        }
                    }
                }
            }
        }
    }
}