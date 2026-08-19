package com.rentpe.rentpe2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BookingConfirmedActivity : AppCompatActivity() {

    private lateinit var txtDate: android.widget.TextView
    private lateinit var txtTime: android.widget.TextView
    private lateinit var txtPropertyName: android.widget.TextView
    private lateinit var txtLocation: android.widget.TextView
    private lateinit var txtAgent: android.widget.TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_booking_confirmed)

        initializeViews()

        loadBookingDetails()

        setupButtons()
    }


    // =====================================================
    // INITIALIZE VIEWS
    // =====================================================

    private fun initializeViews() {

        txtDate = findViewById(R.id.txtDate)

        txtTime = findViewById(R.id.txtTime)

        txtPropertyName =
            findViewById(R.id.txtPropertyName)

        txtLocation =
            findViewById(R.id.txtLocation)

        txtAgent =
            findViewById(R.id.txtAgent)
    }


    // =====================================================
    // LOAD BOOKING DATA
    // =====================================================

    private fun loadBookingDetails() {

        val date =
            intent.getStringExtra("booking_date")

        val time =
            intent.getStringExtra("booking_time")

        val propertyName =
            intent.getStringExtra("property_name")

        val location =
            intent.getStringExtra("property_location")

        val agent =
            intent.getStringExtra("agent_name")


        if (!date.isNullOrEmpty()) {

            txtDate.text = date
        }


        if (!time.isNullOrEmpty()) {

            txtTime.text = time
        }


        if (!propertyName.isNullOrEmpty()) {

            txtPropertyName.text =
                propertyName
        }


        if (!location.isNullOrEmpty()) {

            txtLocation.text =
                location
        }


        if (!agent.isNullOrEmpty()) {

            txtAgent.text =
                agent
        }
    }


    // =====================================================
    // BUTTONS
    // =====================================================

    private fun setupButtons() {

        // Go to My Bookings

        val btnMyBookings =
            findViewById<Button>(
                R.id.btnMyBookings
            )

        btnMyBookings.setOnClickListener {

            Toast.makeText(
                this,
                "My Bookings page coming next",
                Toast.LENGTH_SHORT
            ).show()

            // Later:
            //
            // startActivity(
            //     Intent(
            //         this,
            //         MyBookingsActivity::class.java
            //     )
            // )
        }


        // Return Home

        val btnReturnHome =
            findViewById<Button>(
                R.id.btnReturnHome
            )

        btnReturnHome.setOnClickListener {

            val intent =
                Intent(
                    this,
                    MainActivity::class.java
                )

            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_SINGLE_TOP

            startActivity(intent)

            finish()
        }


        // Message Agent

        val btnMessage =
            findViewById<ImageButton>(
                R.id.btnMessage
            )

        btnMessage.setOnClickListener {

            Toast.makeText(
                this,
                "Opening chat with property agent",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}