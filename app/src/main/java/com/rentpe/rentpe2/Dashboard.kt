package com.rentpe.rentpe2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Dashboard : AppCompatActivity() {

    private lateinit var name: TextView
    private lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dashboard)

        // Get data from MainActivity
        val username = intent.getStringExtra("username")
        val email = intent.getStringExtra("email")

        // Initialize views
        name = findViewById(R.id.name)
        btn = findViewById(R.id.btn1)
         // Display user information
        name.text = "Welcome ${getSharedPreferences("RentPePrefs", MODE_PRIVATE).getString("email", "")}"


        // SharedPreferences
        val sharedPreferences =
            getSharedPreferences("RentPePrefs", MODE_PRIVATE)

        // Logout
        btn.setOnClickListener {

            val builder = androidx.appcompat.app.AlertDialog.Builder(this)

            builder.setTitle("Logout")
            builder.setMessage("Are you sure you want to logout?")

            builder.setPositiveButton("Yes") { dialog, _ ->

                // Set login status to false
                sharedPreferences.edit()
                    .putBoolean("isLoggedIn", false)
                    .apply()

                // Go to MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                finish()

                dialog.dismiss()
            }

            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }

            builder.show()
        }
    }
}