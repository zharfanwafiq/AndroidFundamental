package com.zharfan.androidfundamental.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.zharfan.androidfundamental.databinding.ActivityStyleThemeBinding

class StyleThemeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStyleThemeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStyleThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Google Pixel"
        }

        setOnclickButton()
    }
    private fun setOnclickButton() {
        binding.apply {
            btnBuy.setOnClickListener {
                Toast.makeText(this@StyleThemeActivity, "Beli dong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}