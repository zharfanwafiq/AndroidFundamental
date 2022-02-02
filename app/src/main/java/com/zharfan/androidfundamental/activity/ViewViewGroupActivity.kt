package com.zharfan.androidfundamental.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zharfan.androidfundamental.databinding.ActivityViewViewGroupBinding

class ViewViewGroupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewViewGroupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewViewGroupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Google Pixel"
    }
}