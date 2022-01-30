package com.zharfan.androidfundamental.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zharfan.androidfundamental.R
import com.zharfan.androidfundamental.databinding.ActivityMainBinding
import com.zharfan.androidfundamental.fragment.HomeFragment

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
        val mHomeFragment = HomeFragment()
        val fragment = mFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)


        if (fragment !is  HomeFragment){
            Log.i(TAG, "Fragment name ${HomeFragment::class.java.simpleName}")
            mFragmentManager.beginTransaction()
                .add(R.id.frameContainer,mHomeFragment,HomeFragment::class.java.simpleName)
                .commit()
        }
    }


}