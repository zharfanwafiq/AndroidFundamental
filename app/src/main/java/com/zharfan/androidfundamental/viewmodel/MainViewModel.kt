package com.zharfan.androidfundamental.viewmodel

import android.os.SystemClock
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel :ViewModel() {
    var result = 0.0

    fun calculate(width: String, length: String, height: String){
        result = width.toDouble() * length.toDouble() * height.toDouble()
    }

}