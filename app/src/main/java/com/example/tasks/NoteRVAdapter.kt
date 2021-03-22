package com.example.tasks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class NoteRVAdapter(private val context:Context, private val listener:INotesRVAdapter):RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder> (){
    private val allNote = ArrayList<Note>()

    inner class NoteViewHolder(itemeView: View) : RecyclerView.ViewHolder(itemeView){
        val textView: TextView = itemeView.findViewById<TextView>(R.id.text)
        val deleteButton: ImageView = itemeView.findViewById<ImageView>(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
         val viewHolder =NoteViewHolder( LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
      viewHolder.deleteButton.setOnClickListener{
          listener.onItemClicked(allNote[viewHolder.adapterPosition])
      }
        return viewHolder
    }
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
                 val currentNote = allNote[position]
        holder.textView.text = currentNote.text
    }
    override fun getItemCount(): Int {
        return  allNote.size
    }

    fun updateList(newList: List<Note>){

        allNote.clear()
        allNote.addAll(newList)

        notifyDataSetChanged()

    }
}
interface  INotesRVAdapter{
    fun onItemClicked(note: Note)

}