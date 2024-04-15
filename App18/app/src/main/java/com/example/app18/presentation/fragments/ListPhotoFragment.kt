package com.example.app18.presentation.fragments

import android.graphics.drawable.Drawable
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.app18.R
import com.example.app18.data.model.Photo
import com.example.app18.databinding.FragmentListPhotoBinding
import com.example.app18.presentation.viewmodels.ListPhotoAdapter
import com.example.app18.presentation.viewmodels.ListPhotoViewModel
import com.example.app18.presentation.viewmodels.ListPhotoViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ListPhotoFragment : Fragment() {
    private var _binding: FragmentListPhotoBinding? = null
    private val binding
        get() = _binding!!

    @Inject
    lateinit var listPhotoViewModelFactory: ListPhotoViewModelFactory
    private val viewModel: ListPhotoViewModel by viewModels { listPhotoViewModelFactory }

    private val listPhotoAdapter = ListPhotoAdapter(
        onClick = { photo -> onClickPhoto(photo) }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListPhotoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonTakeNewPhoto.setOnClickListener {
            findNavController().navigate(R.id.action_ListPhotoFragment_to_TakePhotoFragment)
        }

        binding.recyclerView.adapter = listPhotoAdapter

        viewModel.listPhotos.onEach {
            listPhotoAdapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun onClickPhoto(item: Photo) {
        MaterialAlertDialogBuilder(requireContext())
            .setBackground(Drawable.createFromPath(item.uri))
            .setMessage("Дата: ${item.date}")
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}