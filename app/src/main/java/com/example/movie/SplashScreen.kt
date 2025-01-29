package com.example.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashScreen : AppCompatActivity() {
    private lateinit var splashImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        splashImage = findViewById(R.id.splashImage)
        splashImage.alpha = 0f
        splashImage.animate().setDuration(1500L).alpha(1f).withEndAction {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()

            val sharePref = getSharedPreferences("LoginInfo", MODE_PRIVATE)
            if(sharePref.getBoolean("Login", false)){
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                }
            } else {
                startActivity(Intent(this, LogInActivity::class.java))
            }
        }
    }
}