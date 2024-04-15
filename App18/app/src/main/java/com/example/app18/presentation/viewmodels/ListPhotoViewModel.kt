package com.example.app18.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.app18.data.db.Repository
import javax.inject.Inject

class ListPhotoViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val listPhotos = repository.getAllData()
}
