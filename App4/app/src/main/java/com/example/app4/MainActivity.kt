package com.example.app4

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.app4.databinding.ActivityMainBinding
import kotlin.random.Random

private const val MAX_NAME_LENGTH = 40

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.progress = Random.nextInt(101)
        binding.skillsCount.text = "${binding.progressBar.progress}/100"

        binding.switchNotice.setOnClickListener {
            if (binding.switchNotice.isChecked) {
                binding.checkBox1.isEnabled = true
                binding.checkBox2.isEnabled = true
            } else {
                binding.checkBox1.isEnabled = false
                binding.checkBox2.isEnabled = false
            }
        }

        fun isValidInput(): Boolean {
            val name = binding.editNameText.text
            val phone = binding.editPhoneText.text
            return if (!name.isNullOrBlank()
                && name!!.length < 41
                && !phone.isNullOrBlank()
                && (binding.radioButtonMan.isChecked
                        || binding.radioButtonWoman.isChecked)
                && (!binding.switchNotice.isChecked
                        || (binding.switchNotice.isChecked
                        && (binding.checkBox1.isChecked
                        || binding.checkBox2.isChecked)))
            ) {
                return true
            } else false
        }

        binding.editNameText.doOnTextChanged { text, _, _, _ ->
            if (text != null) {
                val len: Int = text.length
                binding.textViewNameInputCount.text = "${len}/${MAX_NAME_LENGTH}"
                if (len in 0..MAX_NAME_LENGTH) {
                    binding.textViewNameInputCount.setTextColor(Color.BLACK)
                    binding.editNameText.setTextColor(Color.BLACK)
                    binding.editName.isErrorEnabled = false
                } else {
                    binding.textViewNameInputCount.setTextColor(Color.RED)
                    binding.editNameText.setTextColor(Color.RED)
                    binding.editName.error = getString(R.string.TooLongName)
                    binding.editName.isErrorEnabled = true
                }
            }
            binding.button.isEnabled = isValidInput()
        }

        binding.editPhoneText.doOnTextChanged { _, _, _, _ ->
            binding.button.isEnabled = isValidInput()
        }

        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            binding.button.isEnabled = isValidInput()
        }

        binding.switchNotice.setOnCheckedChangeListener { _, _ ->
            binding.button.isEnabled = isValidInput()
        }

        binding.checkBox1.setOnCheckedChangeListener { _, _ ->
            binding.button.isEnabled = isValidInput()
        }

        binding.checkBox2.setOnCheckedChangeListener { _, _ ->
            binding.button.isEnabled = isValidInput()
        }

        binding.button.setOnClickListener {
            Toast.makeText(this, getString(R.string.SaveSuccess), Toast.LENGTH_SHORT).show()
        }
    }
}
