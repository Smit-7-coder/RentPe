package com.rentpe.rentpe2

import android.os.Bundle
import android.widget.ImageView
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import android.app.AlertDialog
import android.content.Intent
import android.widget.Button

class PropertyDetailsActivity : AppCompatActivity() {

    private lateinit var imageSlider: ViewPager2

    private lateinit var indicator1: View
    private lateinit var indicator2: View
    private lateinit var indicator3: View
    private lateinit var indicator4: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_property_details)

        // ================= IMAGE SLIDER =================

        imageSlider = findViewById(R.id.propertyImageSlider)

        // ================= INDICATORS =================

        indicator1 = findViewById(R.id.indicator1)
        indicator2 = findViewById(R.id.indicator2)
        indicator3 = findViewById(R.id.indicator3)
        indicator4 = findViewById(R.id.indicator4)

        // ================= PROPERTY IMAGES =================

        val images = listOf(
            R.drawable.property_one,
            R.drawable.property_two,
            R.drawable.property_three,
            R.drawable.property_four
        )

        val adapter = PropertyImageAdapter(images)

        imageSlider.adapter = adapter

        // ================= IMAGE CHANGE =================

        imageSlider.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    updateIndicators(position)
                }
            }
        )

        // ================= BACK BUTTON =================

        val btnBack = findViewById<ImageView>(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }

        // ================= CALL OWNER =================

        val btnCall = findViewById<ImageView>(R.id.btnCall)

        btnCall.setOnClickListener {

            val ownerName = "Smit Sakariya"
            val ownerMobile = "+91 98765 43210"

            AlertDialog.Builder(this)
                .setTitle("Property Owner")
                .setMessage(
                    "$ownerName\n\nMobile Number:\n$ownerMobile"
                )
                .setPositiveButton("OK", null)
                .show()
        }

        // ================= BOOK VISIT =================

        val btnBookVisit =
            findViewById<Button>(R.id.btnBookVisit)

        btnBookVisit.setOnClickListener {

            val intent =
                Intent(
                    this,
                    BookVisitActivity::class.java
                )

            startActivity(intent)
        }
    }

    // ================= UPDATE INDICATORS =================

    private fun updateIndicators(position: Int) {

        indicator1.setBackgroundResource(
            R.drawable.indicator_unselected
        )

        indicator2.setBackgroundResource(
            R.drawable.indicator_unselected
        )

        indicator3.setBackgroundResource(
            R.drawable.indicator_unselected
        )

        indicator4.setBackgroundResource(
            R.drawable.indicator_unselected
        )

        when (position) {

            0 -> indicator1.setBackgroundResource(
                R.drawable.indicator_selected
            )

            1 -> indicator2.setBackgroundResource(
                R.drawable.indicator_selected
            )

            2 -> indicator3.setBackgroundResource(
                R.drawable.indicator_selected
            )

            3 -> indicator4.setBackgroundResource(
                R.drawable.indicator_selected
            )
        }
    }
}