package com.zharfan.androidfundamental.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.zharfan.androidfundamental.R
import com.zharfan.androidfundamental.databinding.ActivityRoomNoteAddUpdateBinding
import com.zharfan.androidfundamental.db.room.NoteEntity
import com.zharfan.androidfundamental.factory.RoomViewModelFactory
import com.zharfan.androidfundamental.utils.DateHelper
import com.zharfan.androidfundamental.viewmodel.RoomNoteAddUpdateViewModel

class RoomNoteAddUpdateActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityRoomNoteAddUpdateBinding.inflate(layoutInflater)
    }

    private lateinit var noteAddUpdateViewModel: RoomNoteAddUpdateViewModel
    private var noteEntity: NoteEntity? = null
    private var isEdit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViewModel()
        setToolbarAndButton()
        setAction()
    }

    private fun initViewModel() {
        noteAddUpdateViewModel = obtainViewModel(this)
    }

    private fun setToolbarAndButton() = with(binding) {
        noteEntity = intent.getParcelableExtra(EXTRA_NOTE)
        if (noteEntity != null) {
            isEdit = true
        } else {
            noteEntity = NoteEntity()
        }

        val actionBarTitle: String
        val btnTitle: String

        if (isEdit) {
            actionBarTitle = "Ubah"
            btnTitle = " Update"
            if (noteEntity != null) {
                noteEntity?.let {
                    etTitle.setText(it.title)
                    etDescription.setText(it.description)
                }
            }
        } else {
            actionBarTitle = "Tambah"
            btnTitle = "Simpan"
        }

        supportActionBar?.apply {
            title = actionBarTitle
            setDisplayHomeAsUpEnabled(true)
        }

        btnSubmit.text = btnTitle
    }

    private fun setAction() = with(binding) {
        btnSubmit.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val description = etDescription.text.toString().trim()

            when {
                title.isEmpty() -> {
                    etTitle.error = "Field Tidak Boleh Kosong"
                }
                description.isEmpty() -> {
                    etDescription.error = "Field Tidak Boleh Kosong"
                }
                else -> {
                    noteEntity?.let {
                        it.title = title
                        it.description = description
                    }
                    if (isEdit) {
                        noteAddUpdateViewModel.update(noteEntity as NoteEntity)
                        showToast("Satu Item Berhasil Diubah")
                    } else {
                        noteEntity?.let {
                            it.date = DateHelper.getCurrentDate()
                        }
                        noteAddUpdateViewModel.insert(noteEntity as NoteEntity)
                        showToast("Satu Item berhasil ditambahkan")
                    }
                    finish()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (isEdit) {
            menuInflater.inflate(R.menu.menu_form, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE)
    }

    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String
        if (isDialogClose) {
            dialogTitle = "Batal"
            dialogMessage = "Apakah Anda ingin membatalkan perubahan Form?"
        } else {
            dialogMessage = "Apakah Anda yakin Ingin menghapus item Ini?"
            dialogTitle = "Hapus"
        }
        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton("Ya") { _, _ ->
                if (!isDialogClose) {
                    noteAddUpdateViewModel.delete(noteEntity as NoteEntity)
                    showToast("Satu Item Berhasil Dihapus")
                }
                finish()
            }
            setNegativeButton("Tidak") { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun obtainViewModel(activity: AppCompatActivity): RoomNoteAddUpdateViewModel {
        val factory = RoomViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[RoomNoteAddUpdateViewModel::class.java]
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val EXTRA_NOTE = "com.zharfan.androidfundamental.activity.EXTRA_NOTE"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }
}
