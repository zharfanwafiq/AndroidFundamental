package com.zharfan.androidfundamental

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.zharfan.androidfundamental.data.Person
import com.zharfan.androidfundamental.databinding.ActivityMainBinding

import com.zharfan.androidfundamental.intent.MoveActivity
import com.zharfan.androidfundamental.intent.MoveActivityWithData
import com.zharfan.androidfundamental.intent.MoveForResultActivity
import com.zharfan.androidfundamental.intent.MoveWithObjectActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}