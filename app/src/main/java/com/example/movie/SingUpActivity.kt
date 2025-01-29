package com.example.movie

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.databinding.ActivitySingUpBinding
import com.google.firebase.auth.FirebaseAuth

class SingUpActivity : AppCompatActivity() {

    private var _binding: ActivitySingUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSingUp.setOnClickListener {
            val email = binding.edtSingUpEmail.text.toString()
            val password = binding.edtSingUpPassword.text.toString()

            if (TextUtils.isEmpty(email)) {
                binding.edtSingUpEmail.error = "Email is Require"
            } else if (TextUtils.isEmpty(password)) {
                binding.edtSingUpPassword.error = "Password is require!"
                binding.edtSingUpPassword.requestFocus()
            } else {
                registerForAccount(email, password)
            }
        }

        binding.btnSingIn.setOnClickListener {
            Intent(this, LogInActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun registerForAccount(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Account has been created, Please log in again",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                    onBackPressed()
                } else {
                    Toast.makeText(
                        this,
                        "Fail!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}