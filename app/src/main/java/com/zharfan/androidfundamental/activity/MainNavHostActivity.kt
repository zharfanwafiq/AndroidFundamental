package com.zharfan.androidfundamental.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zharfan.androidfundamental.databinding.ActivityMainNavHostBinding

class MainNavHostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainNavHostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainNavHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}