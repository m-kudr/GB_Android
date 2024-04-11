package com.example.app17.data.repo

import com.example.app17.data.MarsPhotosList
import com.example.app17.data.api.RetrofitMarsRoverPhotos
import javax.inject.Inject


class MarsPhotoRepository @Inject constructor() {

    /**
     * @param date
     * @return MarsPhotosList
     */
    suspend fun loadMarsPhoto(date: String): MarsPhotosList {
        return RetrofitMarsRoverPhotos.searchMarsPhotosAPI.getMarsPhotosList(date)
    }
}