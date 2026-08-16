package com.rentpe.rentpe2

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {

    private lateinit var etFullName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnCreateAccount: Button
    private lateinit var tvLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_registration)

        // Connect XML views with Kotlin
        etFullName = findViewById(R.id.etFullName)
        etEmail = findViewById(R.id.etRegisterEmail)
        etPhone = findViewById(R.id.etPhone)
        etPassword = findViewById(R.id.etRegisterPassword)
        btnCreateAccount = findViewById(R.id.btnCreateAccount)
        tvLogin = findViewById(R.id.tvLogin)

        // Create Account button
        btnCreateAccount.setOnClickListener {

            validateRegistration()
        }

        // Login button
        tvLogin.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateRegistration() {

        val fullName = etFullName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        val password = etPassword.text.toString()

        // Clear previous errors
        etFullName.error = null
        etEmail.error = null
        etPhone.error = null
        etPassword.error = null

        // Full Name validation
        if (fullName.isEmpty()) {
            etFullName.error = "Please enter your full name"
            etFullName.requestFocus()
            return
        }

        if (fullName.length < 3) {
            etFullName.error = "Name must contain at least 3 characters"
            etFullName.requestFocus()
            return
        }

        // Email validation
        if (email.isEmpty()) {
            etEmail.error = "Please enter your email"
            etEmail.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Please enter a valid email address"
            etEmail.requestFocus()
            return
        }

        // Phone validation
        if (phone.isEmpty()) {
            etPhone.error = "Please enter your phone number"
            etPhone.requestFocus()
            return
        }

        if (!phone.matches(Regex("^[0-9]{10}$"))) {
            etPhone.error = "Phone number must contain exactly 10 digits"
            etPhone.requestFocus()
            return
        }

        // Password validation
        if (password.isEmpty()) {
            etPassword.error = "Please enter a password"
            etPassword.requestFocus()
            return
        }

        if (password.length < 6) {
            etPassword.error = "Password must contain at least 6 characters"
            etPassword.requestFocus()
            return
        }

        // Everything is valid
        Toast.makeText(
            this,
            "All fields are valid!",
            Toast.LENGTH_SHORT
        ).show()
    }
}