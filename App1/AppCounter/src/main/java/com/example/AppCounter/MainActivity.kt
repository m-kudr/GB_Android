package com.example.AppCounter

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.AppCounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val minNumber: Int = 0
    private var currentNumber: Int = 0
    private val maxNumber: Int = 50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text = "Все места свободны"
        binding.buttonMinus.isEnabled = false
        binding.count.text = "0"
        binding.buttonReset.visibility = View.INVISIBLE

        fun checkCount(count: Int) {
            binding.count.text = currentNumber.toString()
            binding.buttonReset.visibility = View.INVISIBLE
            if (count == minNumber) {
                binding.buttonReset.visibility = View.INVISIBLE
                binding.buttonMinus.isEnabled = false
                binding.textView.text = "Все места свободны"
                binding.textView.setTextColor(Color.rgb(0, 150, 136))
            } else {
                if (count < maxNumber) {
                    binding.buttonMinus.isEnabled = true
                    binding.textView.text = "Осталось мест: " + (maxNumber - count)
                    binding.textView.setTextColor(Color.BLUE)
                } else {
                    binding.buttonReset.visibility = View.VISIBLE
                    binding.textView.text = "Пассажиров слишком много!"
                    binding.textView.setTextColor(Color.RED)
                }
            }
        }

        binding.buttonReset.setOnClickListener {
            currentNumber = 0
            checkCount(currentNumber)
        }

        binding.buttonPlus.setOnClickListener {
            checkCount(++currentNumber)
        }

        binding.buttonMinus.setOnClickListener {
            checkCount(--currentNumber)
        }
    }
}