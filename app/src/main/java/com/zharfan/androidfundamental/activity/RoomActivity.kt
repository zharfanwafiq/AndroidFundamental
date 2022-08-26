package com.zharfan.androidfundamental.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zharfan.androidfundamental.adapter.RoomNoteAdapter
import com.zharfan.androidfundamental.databinding.ActivityRoomBinding
import com.zharfan.androidfundamental.factory.RoomViewModelFactory
import com.zharfan.androidfundamental.viewmodel.RoomNoteViewModel

class RoomActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityRoomBinding.inflate(layoutInflater)
    }

    private lateinit var noteAdapter: RoomNoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAdapterView()
        initObserve()
        setAction()
    }

    private fun initAdapterView() = with(binding) {
        noteAdapter = RoomNoteAdapter()
        rvNotes.apply {
            layoutManager = LinearLayoutManager(this@RoomActivity)
            setHasFixedSize(true)
            adapter = noteAdapter
        }

    }

    private fun initObserve() {
        val noteViewModel = obtainViewModel(this)
        noteViewModel.getAllNotes.observe(this) { noteList ->
            if (noteList != null) {
                noteAdapter.setListNotes(noteList)
            }
        }
    }

    private fun setAction() = with(binding) {
        fabAdd.setOnClickListener {
            startActivity(Intent(this@RoomActivity, RoomNoteAddUpdateActivity::class.java))

        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): RoomNoteViewModel {
        val factory = RoomViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[RoomNoteViewModel::class.java]
    }
}