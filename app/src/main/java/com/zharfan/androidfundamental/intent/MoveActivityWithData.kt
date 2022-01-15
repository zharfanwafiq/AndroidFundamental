package com.zharfan.androidfundamental.intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.zharfan.androidfundamental.databinding.ActivityMoveWithDataBinding
import kotlin.properties.Delegates

class MoveActivityWithData : AppCompatActivity() {

    private lateinit var binding: ActivityMoveWithDataBinding
    private var age by Delegates.notNull<Int>()
    private lateinit var name : String
    private lateinit var titleText:String


    companion object {
        const val EXTRA_TITLE ="com.zharfan.androidfundamental.EXTRA_TITLE"
        const val EXTRA_AGE = "com.zharfan.androidfundamental.EXTRA_AGE"
        const val EXTRA_NAME = "com.zharfan.androidfundamental.EXTRA_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoveWithDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBundle()
        showActionBar()
        showData()

    }


    private fun setBundle() {
        titleText = intent.getStringExtra(EXTRA_TITLE).toString()
        age = intent.getIntExtra(EXTRA_AGE,0)
        name = intent.getStringExtra(EXTRA_NAME).toString()
    }
    private fun showData() {
        binding.apply {
            val text  = "Nama say $name ,Umur $age"
            tvActivityIntentWithData.text = text
        }
    }

    private fun showActionBar(){
        supportActionBar?.apply {
            title = titleText
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


}