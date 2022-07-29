package com.zharfan.androidfundamental.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.zharfan.androidfundamental.R
import com.zharfan.androidfundamental.databinding.ActivitySettingsSharedPreferencesBinding
import com.zharfan.androidfundamental.fragment.PreferenceFragment

class SettingsSharedPreferencesActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySettingsSharedPreferencesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment()
    }

    private fun setFragment() {
        val mFragmentManager = supportFragmentManager
        val preferencesFragment = PreferenceFragment()
        val fragment = mFragmentManager.findFragmentByTag(PreferenceFragment::class.java.simpleName)

        if (fragment !is PreferenceFragment) {
            mFragmentManager.commit {
                add(
                    R.id.settingsHolder,
                    preferencesFragment,
                    PreferenceFragment::class.java.simpleName
                )

            }
        }
    }

}