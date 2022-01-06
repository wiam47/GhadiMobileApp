package com.example.ghadidelivery

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.ghadidelivery.Adapters.IntroSliderAdapter
import com.example.ghadidelivery.data.IntroSlide

class IntroSlider : AppCompatActivity() {

    val SHARED_PREFS = "sharedPrefs"
    var KEY = "wasAlreadyIn"
    private var wasAlreadyIn = false

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Find transportation near you",
                "Save your time by requesting a transportation that is near you and heading to the same location you want to move your stuff.",
                R.drawable.order_car
            ),
            IntroSlide(
                "Large/Heavy Quantity",
                "We can deliver luggage of large quantity.. And even heavy ones.",
                R.drawable.deliveries
            ),
            IntroSlide(
                "Safe, fast and cheap cost",
                "Our drivers are trusted so don't worry about your stuff. And since they are heading to the same direction, the cost will be cheap.",
                R.drawable.order_delivered
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadData()
        if (wasAlreadyIn) {
            Intent(applicationContext, Login::class.java).also {
                startActivity(it)
            }
            finish()
        }

        setContentView(R.layout.activity_intro_slider)

        val introSliderViewPager = findViewById<ViewPager2>(R.id.introSliderViewPager)
        introSliderViewPager.adapter = introSliderAdapter

        setupIndicators()
        setCurrentIndicators(0)

        introSliderViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicators(position)
                if(introSliderViewPager.currentItem + 1 == introSliderAdapter.itemCount) {
                    findViewById<LinearLayout>(R.id.skipNextLayout).visibility = View.INVISIBLE
                    findViewById<Button>(R.id.getStartedBtn).visibility = View.VISIBLE
                }
            }
        })

        findViewById<Button>(R.id.nextBtn).setOnClickListener {

            val buttonClick = AlphaAnimation(1f, 0.8f)
            it.startAnimation(buttonClick)

            if(introSliderViewPager.currentItem + 2 == introSliderAdapter.itemCount) {
                introSliderViewPager.currentItem +=1
                findViewById<LinearLayout>(R.id.skipNextLayout).visibility = View.INVISIBLE
                findViewById<Button>(R.id.getStartedBtn).visibility = View.VISIBLE
            }else if (introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount) {
                introSliderViewPager.currentItem +=1
            }
        }

        findViewById<Button>(R.id.getStartedBtn).setOnClickListener {
            saveData()
            Intent(applicationContext, Login::class.java).also {
                startActivity(it)
            }
            finish()
        }

        findViewById<TextView>(R.id.skipTxtV).setOnClickListener {
            saveData()
            Intent(applicationContext, Login::class.java).also {
                startActivity(it)
            }
            finish()
        }


    }

    private fun setupIndicators() {
        val indicatorsContainer = findViewById<LinearLayout>(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView(indicators[i])

        }
    }

    private fun setCurrentIndicators(index: Int) {
        val indicatorsContainer = findViewById<LinearLayout>(R.id.indicatorsContainer)
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView =
                indicatorsContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }


    private fun saveData() {
        val sharedPreferences : SharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(KEY, true)
        editor.apply()
    }
    private fun loadData() {
        val sharedPreferences : SharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        wasAlreadyIn = sharedPreferences.getBoolean(KEY, false)
    }


}