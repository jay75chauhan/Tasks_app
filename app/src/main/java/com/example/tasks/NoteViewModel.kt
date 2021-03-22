package com.example.tasks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) :AndroidViewModel(application) {
private val repository: NoteRepository
    val allNotes :LiveData<List<Note>>
    init {

        val dao = NoteDatabase.getDatabase(application).getNoteDao()
         repository = NoteRepository(dao)

        allNotes = repository.allNote
    }

//kotlin courutind
    fun  deleteNote(notes: Note)= viewModelScope.launch(Dispatchers.IO) {
   repository.delete(notes)
    }

    fun insertNote(notes: Note) = viewModelScope.launch(Dispatchers.IO) {

        repository.insert(notes)
    }


}