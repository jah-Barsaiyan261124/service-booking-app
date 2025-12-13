package com.example.servicebookingapp2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val ivBackground: ImageView = findViewById(R.id.ivBackground)
        val tvConfirmationMessage: TextView = findViewById(R.id.tvConfirmationMessage)

        // Get the data from the intent
        val userName = intent.getStringExtra("USER_NAME")
        val categoryName = intent.getStringExtra("CATEGORY_NAME")

        // Set the confirmation message
        val message = "Thank you, $userName!\n\nYour $categoryName is booked."
        tvConfirmationMessage.text = message

        // Set the background based on the category
        val backgroundResId = when (categoryName) {
            "Birthday Party" -> R.drawable.bg_birthday
            "Wedding Planning" -> R.drawable.bg_wedding
            // Add more cases for other categories
            else -> R.drawable.gradient_1 // A default background
        }
        ivBackground.setImageResource(backgroundResId)
    }
}