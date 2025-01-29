package com.example.movie

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.movie.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {

    private var _binding: ActivityLogInBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSingIn.setOnClickListener {
            val email = binding.edtSingInEmail.text.toString()
            val password = binding.edtSingInPassword.text.toString()

            if(TextUtils.isEmpty(email)){
                binding.edtSingInEmail.error = "Email is require"
                binding.edtSingInEmail.requestFocus()
            } else if(TextUtils.isEmpty(password)) {
                binding.edtSingInPassword.error = "Password is require"
                binding.edtSingInPassword.requestFocus()
            } else {
                Toast.makeText(
                    this,
                    "Log in successful",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.btnSingUp.setOnClickListener {
            Intent(this, SingUpActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}