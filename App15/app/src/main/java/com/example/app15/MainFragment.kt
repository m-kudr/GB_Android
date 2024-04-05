package com.example.app15

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

import com.example.app15.R
import com.example.app15.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels{
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val wordDao = (requireContext().applicationContext as App).db.wordDao()
                return MainViewModel(wordDao) as T
            }
        }
    }
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeOnState()
        subscribeOnChangeData()

        binding.btnSave.setOnClickListener {
            viewModel.onSave(binding.inputText.text.toString())
        }

        binding.btnClear.setOnClickListener {
            viewModel.onDelete()
        }

        binding.inputText.doOnTextChanged { text, _, _, _ ->
            viewModel.changeState(text)
        }
    }

    private fun subscribeOnChangeData() {
        lifecycleScope.launch {
            viewModel.getAllWithLimit.collect { list ->
                binding.wordsBase.text = list.joinToString("\n")
            }
        }
    }

    private fun subscribeOnState() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    State.ERROR -> {
                        binding.btnSave.isEnabled = false
                        binding.textField.isErrorEnabled = true
                        binding.textField.error = resources.getString(R.string.error_text)
                    }

                    State.SUCCESS -> {
                        binding.btnSave.isEnabled = true
                        binding.textField.isErrorEnabled = false
                    }

                    State.START -> binding.btnSave.isEnabled = false
                }
            }
        }
    }
}