package com.mobprog.tugasui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobprog.tugasui.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            if (validateInputs()) {
                val name = binding.nameEditText.text.toString()
                val email = binding.emailEditText.text.toString()
                val password = binding.passwordEditText.text.toString()

                val user = User(name, email, password)
                val intent = Intent(this, LandingActivity::class.java).apply {
                    putExtra("user", user)
                }
                startActivity(intent)
            }
        }

        binding.loginTextView.setOnClickListener {
            finish()
        }

        // Toggle password visibility
        binding.passwordTextInput.setEndIconOnClickListener {
            if (binding.passwordEditText.transformationMethod == null) {
                binding.passwordEditText.transformationMethod = android.text.method.PasswordTransformationMethod.getInstance()
            } else {
                binding.passwordEditText.transformationMethod = null
            }
            binding.passwordEditText.setSelection(binding.passwordEditText.text?.length ?: 0)
        }
    }

    private fun validateInputs(): Boolean {
        val name = binding.nameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        val confirmPassword = binding.confirmPasswordEditText.text.toString()

        return when {
            name.isEmpty() -> {
                binding.nameTextInput.error = "Name cannot be empty"
                false
            }
            email.isEmpty() -> {
                binding.emailTextInput.error = "Email cannot be empty"
                false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
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
            confirmPassword != password -> {
                binding.confirmPasswordTextInput.error = "Passwords don't match"
                false
            }
            else -> {
                binding.nameTextInput.error = null
                binding.emailTextInput.error = null
                binding.passwordTextInput.error = null
                binding.confirmPasswordTextInput.error = null
                true
            }
        }
    }
}