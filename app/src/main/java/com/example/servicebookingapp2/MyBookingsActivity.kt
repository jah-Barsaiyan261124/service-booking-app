package com.example.servicebookingapp2

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyBookingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_bookings)

        val rvBookings = findViewById<RecyclerView>(R.id.rvBookings)
        rvBookings.layoutManager = LinearLayoutManager(this)

        val sharedPrefs = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPrefs.getString("bookings", null)
        val type = object : TypeToken<MutableList<Booking>>() {}.type
        val bookings: MutableList<Booking> = gson.fromJson(json, type) ?: mutableListOf()

        val adapter = BookingAdapter(bookings)
        rvBookings.adapter = adapter
    }
}