package com.example.blacktrivia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class ProfileActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val textViewGreeting = findViewById<TextView>(R.id.textViewGreeting)

        // Retrieve the username from the intent
        val username = intent.getStringExtra("USERNAME")
        textViewGreeting.text = "Welcome, $username!"
    }
}