package com.example.blacktrivia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val textViewGreeting = findViewById<TextView>(R.id.textViewGreeting)

        // Retrieve the username from the intent
        val username = intent.getStringExtra("USERNAME")
        textViewGreeting.text = "Welcome, $username!"

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.menu_profile

        bottomNavigationView.setOnItemSelectedListener {  menuItem ->
            when (menuItem.itemId) {
                R.id.menu_trivia -> {
                    if (this::class != TriviaActivity::class) {
                        val intent = Intent(this, TriviaActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        startActivity(intent)
                        finish()
                    }
                    true
                }
                R.id.menu_profile -> {
                    if (this::class != ProfileActivity::class) {
                        val intent = Intent(this, ProfileActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        startActivity(intent)
                        finish()
                    }
                    true
                }
                else -> false
            }
        }
    }
}