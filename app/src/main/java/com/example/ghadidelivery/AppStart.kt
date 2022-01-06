package com.example.ghadidelivery

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class AppStart : AppCompatActivity() {

    private val SHARED_PREFS = "sharedPrefs"
    private var KEY = "wasAlreadyIn"
    private var wasAlreadyIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_start)

        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                loadData()
            }

            override fun onFinish() {
                if (wasAlreadyIn) {
                    Intent(applicationContext, Login::class.java).also {
                        startActivity(it)
                    }
                    finish()
                } else {
                    Intent(applicationContext, IntroSlider::class.java).also {
                        startActivity(it)
                    }
                    finish()
                }
            }

        }.start()

    }

    private fun loadData() {
        val sharedPreferences : SharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        wasAlreadyIn = sharedPreferences.getBoolean(KEY, false)
    }

}