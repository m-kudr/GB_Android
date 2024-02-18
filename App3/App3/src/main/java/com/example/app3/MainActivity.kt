package com.example.app3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.text1.text = "верхняя строчка, настроенная из кода"
        //binding.text2.text = "нижняя строчка, настроенная из кода"
    }
}