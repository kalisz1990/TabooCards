package com.example.taboocards.ui.splash_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.taboocards.R
import com.example.taboocards.ui.menu_activity.MenuActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel


private const val DELAY_TIME = 3000L

class SplashScreen : AppCompatActivity() {

    private lateinit var splashScreenViewModel: SplashScreenViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        splashScreenViewModel = getViewModel()

        startMainActivityWithDelay()
        addCardsToDb()

    }

    private fun startMainActivityWithDelay() {
        Handler().postDelayed({
            val intent = Intent(this@SplashScreen, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }, DELAY_TIME)
    }

    private fun addCardsToDb(){
        splashScreenViewModel.addCardsToDb()
    }

}