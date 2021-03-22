package com.example.tasks

import android.provider.ContactsContract
import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {



    var allNote:LiveData<List<Note>> = noteDao.getAllNotes()


    suspend fun insert(note:Note){
        noteDao.insert(note)
    }


    suspend fun delete(note:Note){
        noteDao.delete(note)
    }



}