package com.rentpe.rentpe2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    private lateinit var sharedPreferences: android.content.SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val tvSignUp = findViewById<TextView>(R.id.tvSignUp)

        tvSignUp.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        // SharedPreferences
        sharedPreferences = getSharedPreferences("RentPePrefs", MODE_PRIVATE)

        // Check if user is already logged in
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
            finish()
            return
        }

        // Find views from XML
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        // Login button click
        btnLogin.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()

            // Email empty validation
            if (email.isEmpty()) {
                etEmail.error = "Please enter your email"
                return@setOnClickListener
            }

            // Email format validation
            if (!android.util.Patterns.EMAIL_ADDRESS
                    .matcher(email)
                    .matches()
            ) {
                etEmail.error = "Enter a valid email address"
                return@setOnClickListener
            }

            // Password empty validation
            if (password.isEmpty()) {
                etPassword.error = "Please enter your password"
                return@setOnClickListener
            }

            // Password length validation
            if (password.length < 8) {
                etPassword.error =
                    "Password must be minimum 8 characters long"
                return@setOnClickListener
            }

            // Login validation
            if (email == "ssakariya031@rku.ac.in" &&
                password == "12345678"
            ) {

                // Save login status
                sharedPreferences.edit()
                    .putBoolean("isLoggedIn", true)
                    .putString("email", email)
                    .apply()



                Toast.makeText(
                    this,
                    "Login Successful",
                    Toast.LENGTH_SHORT
                ).show()

                // Open Dashboard
                val intent = Intent(this, Dashboard::class.java)

                intent.putExtra("username", "Smit")
                intent.putExtra("email", email)

                startActivity(intent)

                // Prevent going back to Login
                finish()

            } else {

                Toast.makeText(
                    this,
                    "Invalid Email or Password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}