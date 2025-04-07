package com.mobprog.tugasui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobprog.tugasui.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLandingBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>("user")
        user?.let {
            binding.welcomeTextView.text = "Welcome, ${it.name}!"
            binding.emailTextView.text = "Email: ${it.email}"
        }

        binding.logoutButton.setOnClickListener {
            finish()
        }
    }
}