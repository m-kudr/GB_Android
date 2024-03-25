package com.example.easyquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.window.Dialog
import androidx.navigation.fragment.findNavController
import com.example.easyquiz.databinding.FragmentStartBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar = Calendar.getInstance()
        binding.buttonDateOfBirth.setOnClickListener {
            val dateDialog = MaterialDatePicker.Builder.datePicker()
                .setTitleText(resources.getString(R.string.date_dialoge_text))
                .build()
            dateDialog.addOnPositiveButtonClickListener { time ->
                calendar.timeInMillis = time
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val month = calendar.get(Calendar.MONTH) + 1
                val year = calendar.get(Calendar.YEAR)
                var text = ""
                if (month > 9) {
                    text = "$day-$month-$year"
                } else
                    text = "$day.0$month.$year"
                binding.tvDateOfBirth.text = text
                val snackBar =
                    Snackbar.make(binding.buttonDateOfBirth, text, Snackbar.LENGTH_SHORT)
                snackBar.duration = 3000
                snackBar.show()
            }
            dateDialog.show(parentFragmentManager, "Date")
        }

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_quizFragment)
        }
    }
}