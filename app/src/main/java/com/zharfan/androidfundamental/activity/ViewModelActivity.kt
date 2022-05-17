package com.zharfan.androidfundamental.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.zharfan.androidfundamental.databinding.ActivityViewModelBinding
import com.zharfan.androidfundamental.viewmodel.MainViewModel

class ViewModelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewModelBinding
    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayResult()
        setAction()
    }

    private fun displayResult() = with(binding) {
        tvResult.text = viewModel.result.toString()
    }

    private fun setAction() = with(binding){
        btnCalculate.setOnClickListener {
            val width = etWidth.text.toString()
            val length = etLength.text.toString()
            val height = etHeight.text.toString()

            when{
                width.isEmpty() -> {
                    etWidth.error = "Field tidak Boleh Kosong"
                }

                length.isEmpty() -> {
                    etLength.error = "Field tidak Boleh Kosong"
                }

                height.isEmpty() -> {
                    etHeight.error = "Field tidak Boleh Kosong"
                }

                else -> {
                    viewModel.calculate(width,length,height)
                    displayResult()
                }
            }

        }
    }

}