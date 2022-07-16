package com.zharfan.androidfundamental.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.zharfan.androidfundamental.data.UserModel
import com.zharfan.androidfundamental.databinding.ActivitySharedPrefenceBinding
import com.zharfan.androidfundamental.utils.UserPreference

class SharedPrefenceActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySharedPrefenceBinding.inflate(layoutInflater)
    }

    private lateinit var mUserPreference: UserPreference
    private lateinit var userModel: UserModel
    private var isPreferenceEmpty = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mUserPreference = UserPreference(this)
        showExistingPreference()
        setAction()
    }

    private fun showExistingPreference() {
        userModel = mUserPreference.getUser()
        populateView(userModel)
        checkForm(userModel)
    }

    private fun populateView(userModel: UserModel) = with(binding) {
        tvName.text = if (userModel.name.toString().isEmpty()) {
            "Tidak Ada"
        } else {
            userModel.name
        }

        tvEmail.text = if (userModel.email.toString().isEmpty()) {
            "Tidak Ada"
        } else {
            userModel.email
        }

        tvPhoneNumber.text = if (userModel.phoneNumber.toString().isEmpty()) {
            "Tidak Ada"
        } else {
            userModel.phoneNumber
        }
        tvAge.text = userModel.age.toString().ifEmpty {
            "Tidak Ada"
        }

        tvIsLoveMu.text = if (userModel.isLove) {
            "Ya"
        } else {
            "Tidak"
        }
    }

    private fun checkForm(userModel: UserModel) = with(binding) {
        when {
            userModel.name.toString().isNotEmpty() -> {
                btnSave.text = "Ubah"
                isPreferenceEmpty = false
            }
            else -> {
                btnSave.text = "Simpan"
                isPreferenceEmpty = true
            }
        }

    }

    private fun setAction() = with(binding) {
        btnSave.setOnClickListener {
            val intent = Intent(this@SharedPrefenceActivity, FormUserPreferenceActivity::class.java)
            when {
                isPreferenceEmpty -> {
                    intent.apply {
                        putExtra(
                            FormUserPreferenceActivity.EXTRA_TYPE_FORM,
                            FormUserPreferenceActivity.TYPE_ADD
                        )
                        putExtra("USER", userModel)
                    }

                }
                else -> {
                    intent.apply {
                        putExtra(
                            FormUserPreferenceActivity.EXTRA_TYPE_FORM,
                            FormUserPreferenceActivity.TYPE_EDIT
                        )
                        putExtra("USER", userModel)
                    }
                }

            }
            resultLauncher.launch(intent)
        }

    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.data != null && it.resultCode == FormUserPreferenceActivity.RESULT_CODE) {
                userModel =
                    it.data?.getParcelableExtra<UserModel>(FormUserPreferenceActivity.EXTRA_RESULT) as UserModel
                populateView(userModel)
                checkForm(userModel)
            }
        }

}

