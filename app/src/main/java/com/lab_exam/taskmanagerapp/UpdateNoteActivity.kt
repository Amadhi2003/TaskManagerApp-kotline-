package com.lab_exam.taskmanagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lab_exam.taskmanagerapp.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private  lateinit var db: NoteDatabaseHelper
    private var noteId: Int=-1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_update_note)

        db = NoteDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id",-1)
        if(noteId == -1){
            finish()
            return
        }

        val note = db.getNoteByID(noteId)
        binding.updateTitleEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)

        binding.updateSaveButton.setOnClickListener{
            val newTitle = binding.updateTitleEditText.toString()
            val newContent = binding.updateTitleEditText.toString()
            val updateNote = Note(noteId,newTitle,newContent)
            db.updateNotes(updateNote)
            finish()
            Toast.makeText(this,"change saved",Toast.LENGTH_SHORT).show()
        }


    }
}