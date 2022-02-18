package com.zharfan.androidfundamental.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.zharfan.androidfundamental.databinding.ActivityCustomButtonEditTextBinding

class CustomButtonEditTextActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomButtonEditTextBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomButtonEditTextBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButtonEnabled()
    }

    private fun setCustomView() {
        binding.apply {
            val result = etCustomEditText.text
            btnCustomButton.isEnabled = result != null && result.toString().isNotEmpty()
        }
    }

    private fun setButtonEnabled(){
        binding.apply {
            etCustomEditText.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                    TODO("Not yet implemented")
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    setCustomView()
                }

                override fun afterTextChanged(s: Editable?) {
//                    TODO("Not yet implemented")
                }

            })
            btnCustomButton.setOnClickListener{
                Toast.makeText(this@CustomButtonEditTextActivity,etCustomEditText.text, Toast.LENGTH_SHORT).show()
            }
        }
    }
}