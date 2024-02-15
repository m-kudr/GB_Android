package com.example.AppCounter

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.AppCounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val minNumber = 0
        var currentNumber = 0
        val maxNumber = 50

        fun checkCount(count: Int) {
            binding.count.text = currentNumber.toString()
            binding.buttonReset.visibility = View.INVISIBLE
            when (count) {
                0 -> {
                    binding.textView.text = "Все места свободны"
                    binding.textView.setTextColor(Color.rgb(0, 150, 136))
                }
                maxNumber -> {
                    binding.textView.text = "Пассажиров слишком много!"
                    binding.textView.setTextColor(Color.RED)
                }
                else -> {
                    binding.textView.text = "Осталось мест: " + (maxNumber - count)
                    binding.textView.setTextColor(Color.BLUE)
                }
            }
        }

        binding.buttonReset.setOnClickListener {
            currentNumber = 0
            checkCount(currentNumber)
        }

        binding.buttonPlus.setOnClickListener {
            if (currentNumber < maxNumber) {
                currentNumber++
                checkCount(currentNumber)
                if (currentNumber == maxNumber)
                    binding.buttonReset.visibility = View.VISIBLE
            }
        }

        binding.buttonMinus.setOnClickListener {
            if (currentNumber > minNumber) {
                currentNumber--
                checkCount(currentNumber)
            }
        }
    }
}