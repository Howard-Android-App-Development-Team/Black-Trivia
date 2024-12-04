package com.example.blacktrivia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTextUsername = findViewById<EditText>(R.id.editTextUsername)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener {
            val username = editTextUsername.text.toString()
            if (username.isNotEmpty()) {
                // Pass the username to ProfileActivity
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("USERNAME", username)
                startActivity(intent)
            } else {
                editTextUsername.error = "Please enter a username"
            }
        }
    }
}