package com.zharfan.androidfundamental.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zharfan.androidfundamental.fragment.HomeTabLayoutFragment

class SectionPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        val fragment = HomeTabLayoutFragment()
        fragment.arguments = Bundle().apply {
            putInt(HomeTabLayoutFragment.ARG_SECTION_NUMBER, position + 1)
        }
        return fragment
    }
    override fun getItemCount(): Int = 3
}