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

        setExplicitIntent()
        setMoveActivityWithData()
        setMoveActivityWithObject()
        setDialANumber()
        setMoveActivityForResult()

    }




    private fun setExplicitIntent() {
        binding.apply {
            btnMoveActivity.setOnClickListener {
                startActivity(Intent(this@MainActivity, MoveActivity::class.java))
            }

        }
    }
    private fun setMoveActivityWithData() {
        binding.apply {
            btnMoveToActivityWithData.setOnClickListener {
                val intent = Intent(this@MainActivity, MoveActivityWithData::class.java).apply {
                    putExtra(MoveActivityWithData.EXTRA_TITLE,"MoveWithData")
                    putExtra(MoveActivityWithData.EXTRA_AGE,18)
                    putExtra(MoveActivityWithData.EXTRA_NAME,"Asep")
                }
                startActivity(intent)
            }
        }
    }
    private fun setMoveActivityWithObject(){
        binding.apply {
            btnMoveToActivityWithObject.setOnClickListener {
                val  person = Person(
                    "PindahactivityObject",
                    "Zharfan" ,
                    10,
                    "zharfan@gmail.com",
                    "Jambi"
                )
                val intent = Intent(this@MainActivity,MoveWithObjectActivity::class.java).apply {
                    putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)

                }
                startActivity(intent)
            }
        }
    }

    private fun setDialANumber(){
        binding.apply {
            btnDialNumber.setOnClickListener {
                val  phoneNumber = "082185647869"
                startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber")))
            }
        }
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result ->
        if (result.resultCode == MoveForResultActivity.RESULT_CODE && result.data != null){
            val selectedValue = result.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE,0)
            val text = "Hasil $selectedValue"

            binding.apply {
                tvResult.text = text
            }
        }
    }


    private fun setMoveActivityForResult(){
        binding.apply {
            btnMoveToActivityForResult.setOnClickListener {
                val moveForResultIntent = Intent(this@MainActivity, MoveForResultActivity ::class.java)
                resultLauncher.launch(moveForResultIntent)
            }
        }
    }

}