package com.zharfan.androidfundamental.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.zharfan.androidfundamental.R
import com.zharfan.androidfundamental.data.FileModel
import com.zharfan.androidfundamental.databinding.ActivityOpenFileSaveFileBinding
import com.zharfan.androidfundamental.utils.FileHelper

class OpenFileSaveFileActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy {
        ActivityOpenFileSaveFileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setAction()
    }

    private fun setAction() = with(binding) {
        btnNew.setOnClickListener(this@OpenFileSaveFileActivity)
        btnOpen.setOnClickListener(this@OpenFileSaveFileActivity)
        btnSave.setOnClickListener(this@OpenFileSaveFileActivity)
    }

    private fun newFile() = with(binding) {
        etTitle.setText("")
        etDescription.setText("")
        Toast.makeText(this@OpenFileSaveFileActivity, "Clearing File", Toast.LENGTH_SHORT).show()

    }

    private fun showList() = with(binding) {
        val items = fileList()
        val builder = AlertDialog.Builder(this@OpenFileSaveFileActivity).apply {
            setTitle("Pilih File yang diinginkan")
            setItems(items) { _, item ->
                loadData(items[item].toString())
            }
        }
        val alert = builder.create()
        alert.show()
    }

    private fun loadData(title: String) = with(binding) {
        val fileModel = FileHelper.readFromFile(this@OpenFileSaveFileActivity, title)
        etTitle.setText(fileModel.fileName)
        etDescription.setText(fileModel.data)
        Toast.makeText(
            this@OpenFileSaveFileActivity,
            "Loading ${fileModel.fileName} data",
            Toast.LENGTH_SHORT
        ).show()

    }

    private fun saveFile() = with(binding) {
        when {
            etTitle.text.toString().isEmpty() -> etTitle.error = "Title Tidak Boleh Kosong"
            etDescription.text.toString().isEmpty() -> etDescription.error =
                "Deskripsi tidak bolehk kosong"
            else -> {
                val title = etTitle.text.toString()
                val description = etDescription.text.toString()

                val fileModel = FileModel()
                fileModel.apply {
                    fileModel.fileName = title
                    fileModel.data = description
                }
                FileHelper.writeToFile(fileModel, this@OpenFileSaveFileActivity)
                Toast.makeText(
                    this@OpenFileSaveFileActivity,
                    "Saving ${fileModel.fileName}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnNew -> newFile()
            R.id.btnOpen -> showList()
            R.id.btnSave -> saveFile()
        }
    }

}