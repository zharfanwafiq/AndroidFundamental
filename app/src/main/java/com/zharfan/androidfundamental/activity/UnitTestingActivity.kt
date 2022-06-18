package com.zharfan.androidfundamental.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.zharfan.androidfundamental.R
import com.zharfan.androidfundamental.data.CuboidModel
import com.zharfan.androidfundamental.databinding.ActivityUnitTestingBinding
import com.zharfan.androidfundamental.viewmodel.UnitTestingViewModel

class UnitTestingActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUnitTestingBinding
    private lateinit var unitTestingViewModel : UnitTestingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnitTestingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        setAction()
    }

    private fun initViewModel() {
        unitTestingViewModel = UnitTestingViewModel(CuboidModel())

    }

    private fun setAction() = with(binding){
        btnSave.setOnClickListener(this@UnitTestingActivity)
        btnCalculateVolume.setOnClickListener(this@UnitTestingActivity)
        btnCalculateCircumference.setOnClickListener(this@UnitTestingActivity)
        btnCalculateSurfaceArea.setOnClickListener(this@UnitTestingActivity)
    }

    private fun isVisibility(isShow: Boolean) = with(binding){
        btnSave.visibility = if (isShow){
            View.GONE
        }else{
            View.VISIBLE
        }

        btnVisibility.visibility = if (isShow){
            View.VISIBLE
        }else{
            View.GONE
        }
    }
    override fun onClick(v: View) {
        binding.apply {
            val length = etLength.text.toString().trim()
            val width = etWidth.text.toString().trim()
            val height = etHeight.text.toString().trim()

            when{
                TextUtils.isEmpty(length) -> etLength.error = "Field ini tidak boleh kosong"
                TextUtils.isEmpty(width) -> etWidth.error = "Field Ini Tidak Boleh Kosong"
                TextUtils.isEmpty(height) -> etHeight.error = "Field Ini Tidak Boleh Kosong"
                else -> {
                    val valueLength = length.toDouble()
                    val valueHeight = height.toDouble()
                    val valueWidth = width.toDouble()

                    when(v.id){
                        R.id.btnSave -> {
                            unitTestingViewModel.save(valueLength,valueHeight, valueWidth)
                            isVisibility(true)
                        }
                        R.id.btnCalculateVolume -> {
                            val volume = unitTestingViewModel.getVolume().toString()
                            tvResult.text = volume
                            isVisibility(false)
                        }
                        R.id.btnCalculateCircumference -> {
                            val circumference = unitTestingViewModel.getCircumference().toString()
                            tvResult.text = circumference
                            isVisibility(false)
                        }
                        R.id.btnCalculateSurfaceArea->{
                            val surfaceArea = unitTestingViewModel.getSurfaceArea().toString()
                            tvResult.text = surfaceArea
                            isVisibility(false)
                        }
                    }
                }
            }
        }
    }
}