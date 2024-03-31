package com.example.app11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val repository = Repository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate((layoutInflater))

        binding.textView.text = repository.loadText(this)

        binding.btnSave.setOnClickListener {
            val text = binding.editText.text
            if (text != null) {
                repository.saveText(text)

            }
            val textFromRepo = repository.loadText(this)
            binding.textView.text = textFromRepo
        }

        binding.btnClear.setOnClickListener {
            repository.clearText()
        }
    }
}