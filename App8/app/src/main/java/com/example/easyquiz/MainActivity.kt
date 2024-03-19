package com.example.easyquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.airbnb.lottie.LottieAnimationView
import com.example.easyquiz.databinding.ActivityMainBinding
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val lottieView = findViewById<LottieAnimationView>(R.id.lottie_view)
//        val progressIndicator = findViewById<LinearProgressIndicator>(R.id.progress)

//        lottieView.addAnimatorUpdateListener {
//            progressIndicator.progress = ((it.animatedValue as Float) * 100).roundToInt()
//            Log.d("LOTTIE", "${it.animatedValue}")
//        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}