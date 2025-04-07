package com.mobprog.tugasui

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.mobprog.tugasui.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up click listeners
        binding.loginButton.setOnClickListener {
            if (validateInputs()) {
                val email = binding.emailEditText.text.toString()
                val password = binding.passwordEditText.text.toString()

                // In a real app, you would verify credentials against a database
                val intent = Intent(this, LandingActivity::class.java).apply {
                    putExtra("user", User("Logged In User", email, password))
                }
                startActivity(intent)
            }
        }

        binding.registerTextView.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Toggle password visibility
        binding.passwordTextInput.setEndIconOnClickListener {
            if (binding.passwordEditText.transformationMethod == null) {
                binding.passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
            } else {
                binding.passwordEditText.transformationMethod = null
            }
            binding.passwordEditText.setSelection(binding.passwordEditText.text?.length ?: 0)
        }
    }

    private fun validateInputs(): Boolean {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        return when {
            email.isEmpty() -> {
                binding.emailTextInput.error = "Email cannot be empty"
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.emailTextInput.error = "Please enter a valid email"
                false
            }
            password.isEmpty() -> {
                binding.passwordTextInput.error = "Password cannot be empty"
                false
            }
            password.length < 6 -> {
                binding.passwordTextInput.error = "Password must be at least 6 characters"
                false
            }
            else -> {
                binding.emailTextInput.error = null
                binding.passwordTextInput.error = null
                true
            }
        }
    }
}