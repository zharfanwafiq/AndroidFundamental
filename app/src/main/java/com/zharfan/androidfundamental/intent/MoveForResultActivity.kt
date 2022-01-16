package com.zharfan.androidfundamental.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.zharfan.androidfundamental.R
import com.zharfan.androidfundamental.databinding.ActivityMoveForResultBinding

class MoveForResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoveForResultBinding

    companion object {
        const val EXTRA_SELECTED_VALUE =
            "com.zharfan.androidfundamental.intent.EXTRA_SELECTED_VALUE"
        const val RESULT_CODE = 110
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoveForResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showActionBar()
        setActionChoose()

    }

    private fun showActionBar() {
        supportActionBar?.apply {
            title = "Pindah Activity Result"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setActionChoose() {
        binding.apply {
            btnChoose.setOnClickListener{
                if (rgNumber.checkedRadioButtonId > 0) {
                    var value = 0
                    when(rgNumber.checkedRadioButtonId){
                        R.id.rb_50 -> value = 50
                        R.id.rb_100 -> value = 100
                        R.id.rb_150 -> value = 150
                        R.id.rb_200 -> value = 200
                    }

                    val resultIntent = Intent().apply {
                        putExtra(EXTRA_SELECTED_VALUE,value)
                    }
                    setResult(RESULT_CODE,resultIntent)
                    finish()
                }
            }

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home ->onBackPressed()
        }
        return super.onOptionsItemSelected(item)

    }
}