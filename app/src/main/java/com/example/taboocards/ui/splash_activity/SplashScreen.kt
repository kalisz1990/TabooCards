package com.example.taboocards.ui.splash_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.taboocards.R
import com.example.taboocards.ui.menu_activity.MenuActivity


private const val DELAY_TIME = 100L;

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        startMainActivityWithDelay()

    }

    private fun startMainActivityWithDelay() {
        Handler().postDelayed({
            val intent = Intent(this@SplashScreen, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }, DELAY_TIME)
    }

}