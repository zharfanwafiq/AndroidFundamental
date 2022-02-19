package com.zharfan.androidfundamental.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zharfan.androidfundamental.adapter.ListCountryAdapter
import com.zharfan.androidfundamental.data.CountriesData
import com.zharfan.androidfundamental.data.Country
import com.zharfan.androidfundamental.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var listCountryAdapter: ListCountryAdapter
    private var listCountries : ArrayList<Country> = arrayListOf()
    private var title: String = "ASEAN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        showListCountries()
        setButtonOnClick()
        setActionBarTitle(title)
    }

    private fun setActionBarTitle(title:String) {
        supportActionBar?.title = title
    }


    private fun showListCountries() {
        listCountries.addAll(CountriesData.listData)
        listCountryAdapter = ListCountryAdapter(listCountries)
        binding.apply {
            with(rvCountries){
                layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
                adapter = listCountryAdapter
            }
        }
    }

    private fun setButtonOnClick() {
        binding.apply {
            btnLinearLayout.setOnClickListener {
                listCountries.clear()
                showListCountries()
            }
        }
    }


}