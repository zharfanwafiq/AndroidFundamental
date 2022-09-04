package com.zharfan.androidfundamental.activity

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.zharfan.androidfundamental.databinding.ActivityBroadcastReceiverBinding

class BroadcastReceiverActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityBroadcastReceiverBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setAction()
    }

    private fun setAction() = with(binding) {
        btnCheckPermission.setOnClickListener {
            requestPermissionLauncher.launch(Manifest.permission.RECEIVE_SMS)
        }
    }

    var requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(this, "Sms Permission receiver diterima", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Sms Permission receiver ditolak", Toast.LENGTH_SHORT).show()
        }
    }
}