package com.example.tasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INotesRVAdapter {

    private lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adepter = NoteRVAdapter(this,this)
        recyclerView.adapter = adepter


         viewModel = ViewModelProvider(this,
                 ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
                 viewModel.allNotes.observe(this, Observer {list ->
                     list?.let {
                         adepter.updateList(it)
                     }


                 })
    }
    override fun onItemClicked(note: Note) {
       viewModel.deleteNote(note)
         Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_LONG).show()
    }

    fun submitData(view: View) {
        val noteText = input.text.toString()
        if (noteText.isNotEmpty()) {
          viewModel.insertNote(Note(noteText))
            Toast.makeText(this,"$noteText added",Toast.LENGTH_LONG).show()
        }

    }


}