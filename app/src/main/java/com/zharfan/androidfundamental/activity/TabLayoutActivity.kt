package com.zharfan.androidfundamental.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import com.google.android.material.tabs.TabLayoutMediator
import com.zharfan.androidfundamental.R
import com.zharfan.androidfundamental.adapter.SectionPagerAdapter
import com.zharfan.androidfundamental.databinding.ActivityTabLayoutBinding

class TabLayoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 0F
        setData()
    }

    private fun setData() {
        binding.apply {
            val sectionPagerAdapter = SectionPagerAdapter(this@TabLayoutActivity)
            viewPager.adapter = sectionPagerAdapter
            TabLayoutMediator(tabLayout,viewPager){tabs,position ->
                tabs.text = resources.getString(tabTitles[position])
            }.attach()
        }
    }

    companion object{
        @StringRes
        private val tabTitles = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}