package com.example.servicebookingapp2

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            if (password.length != 4) {
                etPassword.error = "Password must be 4 digits"
                return@setOnClickListener
            }

            val sharedPrefs = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            
            // Save the user's credentials
            with(sharedPrefs.edit()) {
                putString(email, password) // Use email as the key for the password
                apply()
            }

            // Add the new email to the set of registered emails
            val emails = sharedPrefs.getStringSet("emails", mutableSetOf()) ?: mutableSetOf()
            emails.add(email)
            with(sharedPrefs.edit()) {
                putStringSet("emails", emails)
                apply()
            }

            Toast.makeText(this, "Sign-up successful! Please log in.", Toast.LENGTH_LONG).show()
            finish() // Go back to the login screen
        }
    }
}