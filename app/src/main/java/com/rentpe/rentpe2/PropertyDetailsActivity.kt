package com.rentpe.rentpe2

import android.os.Bundle
import android.widget.ImageView
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class PropertyDetailsActivity : AppCompatActivity() {

    private lateinit var imageSlider: ViewPager2

    private lateinit var indicator1: View
    private lateinit var indicator2: View
    private lateinit var indicator3: View
    private lateinit var indicator4: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_property_details)

        // Image slider
        imageSlider = findViewById(R.id.propertyImageSlider)

        // Indicators
        indicator1 = findViewById(R.id.indicator1)
        indicator2 = findViewById(R.id.indicator2)
        indicator3 = findViewById(R.id.indicator3)
        indicator4 = findViewById(R.id.indicator4)

        // Property images
        val images = listOf(
            R.drawable.property_one,
            R.drawable.property_two,
            R.drawable.property_three,
            R.drawable.property_four
        )

        val adapter = PropertyImageAdapter(images)

        imageSlider.adapter = adapter

        // Change indicator when image changes
        imageSlider.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    updateIndicators(position)
                }
            }
        )

        // Back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun updateIndicators(position: Int) {

        indicator1.setBackgroundResource(R.drawable.indicator_unselected)
        indicator2.setBackgroundResource(R.drawable.indicator_unselected)
        indicator3.setBackgroundResource(R.drawable.indicator_unselected)
        indicator4.setBackgroundResource(R.drawable.indicator_unselected)

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