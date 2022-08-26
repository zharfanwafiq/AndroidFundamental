package com.zharfan.androidfundamental.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.zharfan.androidfundamental.db.room.NoteEntity
import com.zharfan.androidfundamental.repository.RoomNoteRepository

class RoomNoteAddUpdateViewModel(application: Application) : ViewModel() {

    private val mNoteRepository: RoomNoteRepository = RoomNoteRepository(application)

    fun insert(noteEntity: NoteEntity) = mNoteRepository.insert(noteEntity)
    fun update(noteEntity: NoteEntity) = mNoteRepository.update(noteEntity)
    fun delete(noteEntity: NoteEntity) = mNoteRepository.delete(noteEntity)
}