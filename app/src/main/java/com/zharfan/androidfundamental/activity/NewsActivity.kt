package com.zharfan.androidfundamental.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.zharfan.androidfundamental.R
import com.zharfan.androidfundamental.adapter.news.NewsPagerAdapter
import com.zharfan.androidfundamental.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityNewsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initActionBar()
        initTabLayout()
    }

    private fun initTabLayout()= with(binding) {
        val newsPagerAdapter = NewsPagerAdapter(this@NewsActivity)
        viewPager.adapter = newsPagerAdapter
        TabLayoutMediator(
            tabNews, viewPager
        ){ tab: TabLayout.Tab, position: Int ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

    }

    private fun initActionBar() {
        supportActionBar?.elevation = 0f
    }

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.home, R.string.bookmark)
    }
}