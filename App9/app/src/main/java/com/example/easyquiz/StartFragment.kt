package com.example.easyquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.easyquiz.databinding.FragmentStartBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.Calendar

class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding
    val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_quizFragment)
        }

        binding.buttonDate.setOnClickListener {
            val dateDialog = MaterialDatePicker.Builder.datePicker()
                .setTitleText(resources.getString(R.string.birthday_title))
                .build()
            dateDialog.addOnPositiveButtonClickListener { timeInMillis ->
                calendar.timeInMillis = timeInMillis
            }
            dateDialog.show(FragmentStartBinding.bind(binding), "DatePicker")
        }
    }
}