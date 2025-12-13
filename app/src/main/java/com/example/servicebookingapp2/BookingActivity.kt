package com.example.servicebookingapp2

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Calendar

class BookingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val tvBookingTitle: TextView = findViewById(R.id.tvBookingTitle)
        val etName: EditText = findViewById(R.id.etName)
        val etPhone: EditText = findViewById(R.id.etPhone)
        val etEventDate: EditText = findViewById(R.id.etEventDate)
        val btnConfirmBooking: Button = findViewById(R.id.btnConfirmBooking)

        val categoryName = intent.getStringExtra("CATEGORY_NAME")
        tvBookingTitle.text = "Book: $categoryName"

        etEventDate.setOnClickListener {
            showDatePickerDialog(etEventDate)
        }

        btnConfirmBooking.setOnClickListener {
            val userName = etName.text.toString()
            val phoneNumber = etPhone.text.toString()
            val eventDate = etEventDate.text.toString()

            if (userName.isEmpty()) {
                etName.error = "Please enter your name"
                return@setOnClickListener
            }
            
            if (phoneNumber.length != 10) {
                etPhone.error = "Please enter a 10-digit phone number"
                return@setOnClickListener
            }
            
            if (eventDate.isEmpty()) {
                etEventDate.error = "Please select a date"
                return@setOnClickListener
            }

            val newBooking = Booking(categoryName!!, userName, eventDate, phoneNumber)
            saveBooking(newBooking)

            val intent = Intent(this, ConfirmationActivity::class.java)
            intent.putExtra("USER_NAME", userName)
            intent.putExtra("CATEGORY_NAME", categoryName)
            startActivity(intent)
            finish()
        }
    }

    private fun showDatePickerDialog(etEventDate: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                etEventDate.setText(selectedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    private fun saveBooking(booking: Booking) {
        val sharedPrefs = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPrefs.getString("bookings", null)
        val type = object : TypeToken<MutableList<Booking>>() {}.type
        val bookings: MutableList<Booking> = gson.fromJson(json, type) ?: mutableListOf()
        bookings.add(booking)
        val newJson = gson.toJson(bookings)
        with(sharedPrefs.edit()) {
            putString("bookings", newJson)
            apply()
        }
    }
}