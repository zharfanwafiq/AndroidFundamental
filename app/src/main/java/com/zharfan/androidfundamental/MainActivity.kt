package com.zharfan.androidfundamental

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.zharfan.androidfundamental.data.Person
import com.zharfan.androidfundamental.databinding.ActivityMainBinding
import com.zharfan.androidfundamental.debug.DebugActivity
import com.zharfan.androidfundamental.fragment.HomeFragment

import com.zharfan.androidfundamental.intent.MoveActivity
import com.zharfan.androidfundamental.intent.MoveActivityWithData
import com.zharfan.androidfundamental.intent.MoveForResultActivity
import com.zharfan.androidfundamental.intent.MoveWithObjectActivity

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