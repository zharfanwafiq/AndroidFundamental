package com.zharfan.androidfundamental.utils

import android.database.Cursor
import com.zharfan.androidfundamental.data.Note
import com.zharfan.androidfundamental.db.sqlite.DatabaseContract

object MappingHelper {
    fun mapCursorToArrayList(notesCursor: Cursor?):ArrayList<Note>{
        val notesList = ArrayList<Note>()
        notesCursor?.apply {
            while (moveToNext()){
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID))
                val title = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE))
                val description = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION))
                val date = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.DATE))
                notesList.add(
                    Note(
                        id,
                        title,
                        description,
                        date
                    )
                )
            }
        }
        return  notesList
    }
}