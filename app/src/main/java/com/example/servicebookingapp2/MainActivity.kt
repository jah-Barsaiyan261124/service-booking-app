package com.example.servicebookingapp2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        tvTitle.text = "Book Your Service"

        val rvCategories = findViewById<RecyclerView>(R.id.rvCategories)
        rvCategories.layoutManager = GridLayoutManager(this, 2)

        val categories = listOf(
            // Event Categories
            Category("Birthday Party", R.drawable.ic_launcher_foreground),
            Category("Wedding Planning", R.drawable.ic_launcher_foreground),
            Category("Engagement", R.drawable.ic_launcher_foreground),
            Category("Festive Decoration", R.drawable.ic_launcher_foreground),
            Category("DJ & Sound Setup", R.drawable.ic_launcher_foreground),
            Category("Catering", R.drawable.ic_launcher_foreground),
            Category("Photography", R.drawable.ic_launcher_foreground),
            Category("Balloon Decoration", R.drawable.ic_launcher_foreground),
            Category("Mehndi Artist", R.drawable.ic_launcher_foreground),
            Category("Stage Decoration", R.drawable.ic_launcher_foreground),
            Category("Cake Designer", R.drawable.ic_launcher_foreground),

            // Home Service Categories
            Category("Home Cleaning", R.drawable.ic_launcher_foreground),
            Category("Plumbing", R.drawable.ic_launcher_foreground),
            Category("Electrician", R.drawable.ic_launcher_foreground),
            Category("Painting", R.drawable.ic_launcher_foreground)
        )

        val adapter = CategoryAdapter(categories) { category ->
            val intent = Intent(this, BookingActivity::class.java)
            intent.putExtra("CATEGORY_NAME", category.name)
            startActivity(intent)
        }
        rvCategories.adapter = adapter

        val btnMyBookings = findViewById<Button>(R.id.btnMyBookings)
        btnMyBookings.setOnClickListener {
            val intent = Intent(this, MyBookingsActivity::class.java)
            startActivity(intent)
        }
    }
}