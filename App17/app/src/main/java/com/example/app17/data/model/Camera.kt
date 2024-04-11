package com.example.app17.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Camera(
    val name: String
) : Parcelable
