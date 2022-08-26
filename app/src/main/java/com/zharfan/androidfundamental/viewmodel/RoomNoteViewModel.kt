package com.zharfan.androidfundamental.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zharfan.androidfundamental.db.room.NoteEntity
import com.zharfan.androidfundamental.repository.RoomNoteRepository

class RoomNoteViewModel(application: Application):ViewModel() {
    private val mNoteRepository:RoomNoteRepository = RoomNoteRepository(application)

    val getAllNotes: LiveData<List<NoteEntity>> = mNoteRepository.getAllNotes
}