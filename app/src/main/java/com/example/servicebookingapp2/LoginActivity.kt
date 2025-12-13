package com.example.servicebookingapp2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<AutoCompleteTextView>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvSignUp = findViewById<TextView>(R.id.tvSignUp)

        val sharedPrefs = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        // Load and display the list of saved emails
        val emails = sharedPrefs.getStringSet("emails", null)
        if (emails != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, emails.toList())
            etEmail.setAdapter(adapter)
        }

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val savedPassword = sharedPrefs.getString(email, null) // Get password using email as key

            if (password == savedPassword) {
                // Save the last used email
                with(sharedPrefs.edit()) {
                    putString("last_email", email)
                    apply()
                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                etPassword.error = "Incorrect password"
            }
        }

        tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        // Set the last used email in the field
        val lastEmail = sharedPrefs.getString("last_email", null)
        if (lastEmail != null) {
            etEmail.setText(lastEmail)
        }
    }
}