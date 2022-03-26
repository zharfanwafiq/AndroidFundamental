package com.zharfan.androidfundamental.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zharfan.androidfundamental.R
import com.zharfan.androidfundamental.databinding.ActivityMainBinding
import com.zharfan.androidfundamental.fragment.Home1Fragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object{
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showHomeFragment()

    }

    private fun showHomeFragment() {
        val mFragmentManager = supportFragmentManager
        val mHomeFragment = Home1Fragment()
        val fragment = mFragmentManager.findFragmentByTag(Home1Fragment::class.java.simpleName)


        if (fragment !is  Home1Fragment){
            Log.i(TAG, "Fragment name ${Home1Fragment::class.java.simpleName}")
            mFragmentManager.beginTransaction()
                .add(R.id.frameContainer,mHomeFragment,Home1Fragment::class.java.simpleName)
                .commit()
        }
    }


}