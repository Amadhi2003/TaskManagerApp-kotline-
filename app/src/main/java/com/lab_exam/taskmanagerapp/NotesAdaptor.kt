package com.lab_exam.taskmanagerapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NotesAdaptor (private var notes:List<Note>, context: Context): RecyclerView.Adapter<NotesAdaptor.NoteViewHolder>() {

    private val db: NoteDatabaseHelper = NoteDatabaseHelper(context)

    class  NoteViewHolder(itemViwe: View): RecyclerView.ViewHolder(itemViwe){
        val titleTextView:TextView = itemViwe.findViewById(R.id.titleTextView)
        val contentTextView:TextView = itemViwe.findViewById(R.id.contentTextView)
        val updateButtion: ImageView = itemViwe.findViewById(R.id.updateButtion)
        val deleteButton:ImageView = itemViwe.findViewById(R.id.deleteButtion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return  NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesAdaptor.NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content

        holder.updateButtion.setOnClickListener{
            val intent =Intent(holder.itemView.context, UpdateNoteActivity::class.java).apply {
                putExtra("note_id",note.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.deleteButton.setOnClickListener{
            db.deleteNote(note.id)
            refreshedData(db.getAllNotes())
            Toast.makeText(holder.itemView.context,"note deleted",Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshedData(newNotes:List<Note>){
        notes = newNotes
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = notes.size
}