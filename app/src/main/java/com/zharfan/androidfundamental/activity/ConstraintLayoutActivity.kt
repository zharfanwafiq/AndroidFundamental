package com.zharfan.androidfundamental.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.zharfan.androidfundamental.databinding.ActivityConstraintLayoutBinding

class ConstraintLayoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConstraintLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConstraintLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Google Pixel"

        setOnclickButton()
    }

    private fun setOnclickButton() {
        binding.apply {
            btnBuy.setOnClickListener {
                Toast.makeText(this@ConstraintLayoutActivity, "Beli dong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}