package com.rentpe.rentpe2

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import android.content.Intent

class BookVisitActivity : AppCompatActivity() {

    private lateinit var calendarGrid: GridLayout
    private lateinit var txtMonth: TextView
    private lateinit var txtBookingDate: TextView

    private lateinit var btnPreviousMonth: ImageButton
    private lateinit var btnNextMonth: ImageButton
    private lateinit var btnConfirmBooking: Button

    private lateinit var visitorCard: View
    private lateinit var noteCard: View

    private lateinit var txtVisitors: TextView
    private lateinit var txtNote: TextView

    private val calendar = Calendar.getInstance()

    private var selectedDay = calendar.get(Calendar.DAY_OF_MONTH)

    private var selectedTime = "02:00 PM"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_book_visit)

        initializeViews()

        setupButtons()

        setupTimeSlots()

        setupVisitDetails()

        createCalendar()

        updateBookingSummary()
    }


    // =========================================================
    // INITIALIZE VIEWS
    // =========================================================

    private fun initializeViews() {

        calendarGrid = findViewById(R.id.calendarGrid)

        txtMonth = findViewById(R.id.txtMonth)

        txtBookingDate = findViewById(R.id.txtBookingDate)

        btnPreviousMonth = findViewById(R.id.btnPreviousMonth)

        btnNextMonth = findViewById(R.id.btnNextMonth)

        btnConfirmBooking = findViewById(R.id.btnConfirmBooking)

        visitorCard = findViewById(R.id.visitorCard)

        noteCard = findViewById(R.id.noteCard)

        txtVisitors = findViewById(R.id.txtVisitors)

        txtNote = findViewById(R.id.txtNote)
    }


    // =========================================================
    // BUTTONS
    // =========================================================

    private fun setupButtons() {

        // Back button
        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {

            finish()
        }


        // Notification button

        // Previous month
        btnPreviousMonth.setOnClickListener {

            calendar.add(Calendar.MONTH, -1)

            selectedDay = 1

            createCalendar()

            updateBookingSummary()
        }


        // Next month
        btnNextMonth.setOnClickListener {

            calendar.add(Calendar.MONTH, 1)

            selectedDay = 1

            createCalendar()

            updateBookingSummary()
        }


        // Confirm booking
        btnConfirmBooking.setOnClickListener {

            confirmBooking()
        }
    }


    // =========================================================
    // CREATE CALENDAR
    // =========================================================

    private fun createCalendar() {

        calendarGrid.removeAllViews()


        // Month title

        val monthFormat =
            SimpleDateFormat(
                "MMMM yyyy",
                Locale.getDefault()
            )

        txtMonth.text =
            monthFormat.format(calendar.time)


        // Week names

        val weekNames = arrayOf(
            "Mo",
            "Tu",
            "We",
            "Th",
            "Fr",
            "Sa",
            "Su"
        )


        for (dayName in weekNames) {

            val textView = TextView(this)

            textView.text = dayName

            textView.textSize = 16f

            textView.setTextColor(
                Color.parseColor("#777C88")
            )

            textView.gravity = Gravity.CENTER


            val params =
                GridLayout.LayoutParams()

            params.width = 0

            params.height = 55

            params.columnSpec =
                GridLayout.spec(
                    GridLayout.UNDEFINED,
                    1f
                )

            textView.layoutParams = params

            calendarGrid.addView(textView)
        }


        // Find first day of month

        val tempCalendar =
            calendar.clone() as Calendar

        tempCalendar.set(
            Calendar.DAY_OF_MONTH,
            1
        )

        val firstDay =
            tempCalendar.get(
                Calendar.DAY_OF_WEEK
            )


        // Convert Sunday = 1
        // Monday = 0

        val startingPosition =
            if (firstDay == Calendar.SUNDAY) {

                6

            } else {

                firstDay - 2
            }


        // Maximum days

        val maxDays =
            calendar.getActualMaximum(
                Calendar.DAY_OF_MONTH
            )


        // Empty spaces before day 1

        for (i in 0 until startingPosition) {

            val emptyView = TextView(this)

            val params =
                GridLayout.LayoutParams()

            params.width = 0

            params.height = 65

            params.columnSpec =
                GridLayout.spec(
                    GridLayout.UNDEFINED,
                    1f
                )

            emptyView.layoutParams = params

            calendarGrid.addView(emptyView)
        }


        // Create days

        for (day in 1..maxDays) {

            val dayView = TextView(this)

            dayView.text =
                day.toString()

            dayView.textSize = 18f

            dayView.gravity =
                Gravity.CENTER


            val params =
                GridLayout.LayoutParams()

            params.width = 0

            params.height = 65

            params.columnSpec =
                GridLayout.spec(
                    GridLayout.UNDEFINED,
                    1f
                )

            params.setMargins(
                3,
                3,
                3,
                3
            )

            dayView.layoutParams = params


            // Selected day

            if (day == selectedDay) {

                dayView.setTextColor(
                    Color.WHITE
                )

                dayView.background =
                    createSelectedDateBackground()

            } else {

                dayView.setTextColor(
                    Color.parseColor(
                        "#202328"
                    )
                )
            }


            // Click day

            dayView.setOnClickListener {

                selectedDay = day

                createCalendar()

                updateBookingSummary()
            }


            calendarGrid.addView(dayView)
        }
    }


    // =========================================================
    // SELECTED DATE BACKGROUND
    // =========================================================

    private fun createSelectedDateBackground():
            GradientDrawable {

        val drawable =
            GradientDrawable()

        drawable.shape =
            GradientDrawable.RECTANGLE

        drawable.cornerRadius = 20f

        drawable.setColor(
            Color.parseColor(
                "#0758D5"
            )
        )

        return drawable
    }


    // =========================================================
    // TIME SLOTS
    // =========================================================

    private fun setupTimeSlots() {

        val timeViews =
            listOf(

                findViewById<TextView>(
                    R.id.time10
                ),

                findViewById<TextView>(
                    R.id.time1130
                ),

                findViewById<TextView>(
                    R.id.time1400
                ),

                findViewById<TextView>(
                    R.id.time1530
                ),

                findViewById<TextView>(
                    R.id.time1700
                ),

                findViewById<TextView>(
                    R.id.time1830
                )
            )


        for (timeView in timeViews) {

            timeView.setOnClickListener {

                selectedTime =
                    timeView.text.toString()

                updateTimeSlotUI(
                    timeViews
                )

                updateBookingSummary()
            }
        }


        // Default selection
        updateTimeSlotUI(timeViews)
    }


    // =========================================================
    // UPDATE TIME UI
    // =========================================================

    private fun updateTimeSlotUI(
        timeViews: List<TextView>
    ) {

        for (timeView in timeViews) {

            if (
                timeView.text.toString() ==
                selectedTime
            ) {

                val drawable =
                    GradientDrawable()

                drawable.setColor(
                    Color.parseColor(
                        "#EAF1FF"
                    )
                )

                drawable.setStroke(
                    3,
                    Color.parseColor(
                        "#0758D5"
                    )
                )

                drawable.cornerRadius = 25f

                timeView.background =
                    drawable

                timeView.setTextColor(
                    Color.parseColor(
                        "#0758D5"
                    )
                )

            } else {

                timeView.background =
                    getDrawable(
                        R.drawable.bg_time_slot
                    )

                timeView.setTextColor(
                    Color.parseColor(
                        "#454A55"
                    )
                )
            }
        }
    }


    // =========================================================
    // VISIT DETAILS
    // =========================================================

    private fun setupVisitDetails() {

        visitorCard.setOnClickListener {

            showVisitorDialog()
        }


        noteCard.setOnClickListener {

            showNoteDialog()
        }
    }


    // =========================================================
    // VISITOR DIALOG
    // =========================================================

    private fun showVisitorDialog() {

        val options =
            arrayOf(
                "Just me (1 person)",
                "2 people",
                "3 people",
                "4 people",
                "5 people"
            )


        AlertDialog.Builder(this)
            .setTitle("Select Visitors")
            .setItems(options) { _, which ->

                txtVisitors.text =
                    options[which]
            }
            .show()
    }


    // =========================================================
    // NOTE DIALOG
    // =========================================================

    private fun showNoteDialog() {

        val input =
            EditText(this)

        input.hint =
            "Enter your special request"

        input.setPadding(
            30,
            20,
            30,
            20
        )


        AlertDialog.Builder(this)
            .setTitle("Add Note")
            .setView(input)

            .setPositiveButton("Save") { _, _ ->

                val note =
                    input.text
                        .toString()
                        .trim()


                if (note.isNotEmpty()) {

                    txtNote.text =
                        note

                } else {

                    txtNote.text =
                        "Special request"
                }
            }

            .setNegativeButton(
                "Cancel",
                null
            )

            .show()
    }


    // =========================================================
    // UPDATE BOOKING SUMMARY
    // =========================================================

    private fun updateBookingSummary() {

        val dateFormat =
            SimpleDateFormat(
                "EEE, dd MMM",
                Locale.getDefault()
            )


        val selectedCalendar =
            calendar.clone() as Calendar


        selectedCalendar.set(
            Calendar.DAY_OF_MONTH,
            selectedDay
        )


        txtBookingDate.text =
            "${dateFormat.format(selectedCalendar.time)} at $selectedTime"
    }


    // =========================================================
    // CONFIRM BOOKING
    // =========================================================

    private fun confirmBooking() {

        val dateFormat =
            SimpleDateFormat(
                "EEEE, MMM dd",
                Locale.getDefault()
            )

        val selectedCalendar =
            calendar.clone() as Calendar

        selectedCalendar.set(
            Calendar.DAY_OF_MONTH,
            selectedDay
        )

        val selectedDate =
            dateFormat.format(
                selectedCalendar.time
            )


        val intent =
            Intent(
                this,
                BookingConfirmedActivity::class.java
            )


        intent.putExtra(
            "booking_date",
            selectedDate
        )

        intent.putExtra(
            "booking_time",
            selectedTime
        )

        intent.putExtra(
            "property_name",
            "Skyline Azure Penthouse"
        )

        intent.putExtra(
            "property_location",
            "Bandra West, Mumbai"
        )

        intent.putExtra(
            "agent_name",
            "Jonathan Sterling"
        )


        startActivity(intent)

        finish()
    }
}