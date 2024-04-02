package com.example.app14

sealed class State {
    data object Loading : State()
    data object Success : State()
    data class Error(val nameError: String = "The request failed") : State()
}
