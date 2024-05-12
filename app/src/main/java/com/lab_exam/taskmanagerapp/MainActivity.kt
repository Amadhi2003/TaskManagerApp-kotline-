package com.lab_exam.taskmanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lab_exam.taskmanagerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    private lateinit var  db: NoteDatabaseHelper
    private lateinit var  notesAdaptor: NotesAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)
        notesAdaptor = NotesAdaptor(db.getAllNotes(), this)

        binding.noteRecylerView.layoutManager = LinearLayoutManager(this)
        binding.noteRecylerView.adapter = notesAdaptor

        binding.addButton.setOnClickListener() {
            val intent = Intent(this, AddNoteActivity::class.java);
            startActivity(intent)
        }
    }

        override fun onResume() {
            super.onResume()
            notesAdaptor.refreshedData(db.getAllNotes())
        }

}