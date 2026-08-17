package com.rentpe.rentpe2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dashboard)

        val btnBookVisitOne = findViewById<Button>(R.id.btnBookVisitOne)

        btnBookVisitOne.setOnClickListener {

            val intent = Intent(this, PropertyDetailsActivity::class.java)

            startActivity(intent)
        }
    }
}