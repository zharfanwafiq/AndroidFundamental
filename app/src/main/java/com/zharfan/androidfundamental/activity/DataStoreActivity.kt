package com.zharfan.androidfundamental.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.zharfan.androidfundamental.databinding.ActivityDataStoreBinding
import com.zharfan.androidfundamental.factory.ViewModelFactory
import com.zharfan.androidfundamental.preference.SettingPreferences
import com.zharfan.androidfundamental.viewmodel.DataStoreViewModel

class DataStoreActivity : AppCompatActivity() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private val binding by lazy {
        ActivityDataStoreBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        switchTheme()
    }

    private fun switchTheme() = with(binding) {

        val pref = SettingPreferences.getInstance(dataStore)
        val dataStoreViewModel = ViewModelProvider(
            this@DataStoreActivity,
            ViewModelFactory(pref)
        )[DataStoreViewModel::class.java]

        dataStoreViewModel.getThemeSettings().observe(this@DataStoreActivity) { isDarkModeActive ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }

        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            dataStoreViewModel.saveThemeSettings(isChecked)
        }
    }
}