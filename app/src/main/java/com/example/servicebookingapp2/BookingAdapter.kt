package com.example.servicebookingapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookingAdapter(private val bookings: List<Booking>) : RecyclerView.Adapter<BookingAdapter.BookingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            BookingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.booking_item, parent, false)
        return BookingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        holder.bind(bookings[position])
    }

    override fun getItemCount() = bookings.size

    class BookingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvCategoryName: TextView = itemView.findViewById(R.id.tvCategoryName)
        private val tvUserName: TextView = itemView.findViewById(R.id.tvUserName)
        private val tvEventDate: TextView = itemView.findViewById(R.id.tvEventDate)
        private val tvPhoneNumber: TextView = itemView.findViewById(R.id.tvPhoneNumber)

        fun bind(booking: Booking) {
            tvCategoryName.text = booking.categoryName
            tvUserName.text = "Booked by: ${booking.userName}"
            tvEventDate.text = "Date: ${booking.eventDate}"
            tvPhoneNumber.text = "Contact: ${booking.phoneNumber}"
        }
    }
}