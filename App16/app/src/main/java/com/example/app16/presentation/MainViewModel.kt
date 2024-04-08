package com.example.app16.presentation

import androidx.lifecycle.ViewModel
import com.example.app16.data.UsefulActivityDto
import com.example.app16.domain.GetUsefulActivityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsefulActivityUseCase: GetUsefulActivityUseCase
) : ViewModel() {
    private val defaultActivity = UsefulActivityDto(
        "activity",
        "type",
        0,
        1.0F,
        "link",
        "key",
        1.0F
    )
    private val _activityFlow = MutableStateFlow(defaultActivity)
    val activityFlow = _activityFlow.asStateFlow()

    suspend fun reloadUsefulActivity() {
        _activityFlow.value = getUsefulActivityUseCase.execute()
    }
}