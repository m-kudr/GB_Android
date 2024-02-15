package com.example.app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val maxNumber = 10
        var currentNumber = 0
        val minNumber = 0

        //binding.textView3.text = "@strings/"
        binding.buttonReset.setOnClickListener {
            currentNumber = 0
            binding.count.text = "0"
        }

        binding.buttonPlus.setOnClickListener {
            if (currentNumber < maxNumber) {
                currentNumber++
                binding.count.text = currentNumber.toString()
            }// else
                //binding.buttonReset.visibility = true
        }

        binding.buttonMinus.setOnClickListener {
            if (currentNumber > minNumber) {
                currentNumber--
                binding.count.text = currentNumber.toString()
            }
        }
    }
}