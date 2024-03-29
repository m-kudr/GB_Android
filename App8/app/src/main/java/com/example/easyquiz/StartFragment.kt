package com.example.easyquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.window.Dialog
import androidx.navigation.fragment.findNavController
import com.example.easyquiz.databinding.FragmentStartBinding

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

//        val dialog = Dialog(requireActivity(), R.style.)
//        dialog.setTitle()
//        dialog.setContentView()

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_quizFragment)
        }
    }
}