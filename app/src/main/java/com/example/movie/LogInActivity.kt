package com.example.movie

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth

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
                loginAccount(email, password)
            }
        }

        binding.txvForgetPassword.setOnClickListener {
            val email = binding.edtSingInEmail.text.toString()

            if (TextUtils.isEmpty(email)) {
                binding.edtSingInEmail.error = "Email is require"
                binding.edtSingInEmail.requestFocus()
            } else {
                forgetPassword(email)
            }
        }

        binding.btnSingUp.setOnClickListener {
            Intent(this, SingUpActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun loginAccount(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    Toast.makeText(this, "Log in success", Toast.LENGTH_SHORT).show()
                    Intent(this, MainActivity::class.java).also {
                        startActivity(it)
                    }
                } else {
                    Toast.makeText(this, "Log in fail", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun forgetPassword(email: String) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Intent(this, ForgetPasswordActivity::class.java).also {
                    startActivity(it)
                }
            }
            else {
                Toast.makeText(this, "Reset fail", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}