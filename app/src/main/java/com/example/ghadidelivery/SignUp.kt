package com.example.ghadidelivery

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.TextView

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val builder = SpannableStringBuilder()

        val blue = "Inscrivez-vous en tant que chauffeur"
        val redSpannable = SpannableString(blue)
        redSpannable.setSpan(ForegroundColorSpan(Color.BLUE), 0, blue.length, 14)
        builder.append(redSpannable)
      //  blue.setText(builder, TextView.BufferType.SPANNABLE)
    }
}