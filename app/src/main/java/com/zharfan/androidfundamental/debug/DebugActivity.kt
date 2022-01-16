package com.zharfan.androidfundamental.debug

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.zharfan.androidfundamental.R
import java.lang.StringBuilder



class DebugActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnSetValue: Button
    private lateinit var tvText : TextView

    private var names = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug)

        showActionBar()
        setInitView()
        setData()
    }

    private fun setData() {
        names.add("Zharfan")
        names.add("Wafiq")
        names.add("ganteng")
        names.add("sekali")
    }

    private fun setInitView() {
        tvText = findViewById(R.id.tvText)
        btnSetValue = findViewById(R.id.btnSetValue)
        btnSetValue.setOnClickListener(this)
    }

    private fun showActionBar() {
        supportActionBar?.apply {
            title = "Debugging & Logging"
            setDisplayHomeAsUpEnabled(true)
        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home ->onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btnSetValue -> {
                Log.d("DebugActivity",names.toString())
                val name = StringBuilder()
                for (i in 0..3){
                    name.append(names[i]).append("\n")
                }
                tvText.text = name.toString()
            }
        }

    }


}