package com.example.app17.data

import android.os.Parcelable
import com.example.app17.data.model.MarsPhoto
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarsPhotosList(
    val photos: List<MarsPhoto>
) : Parcelable
