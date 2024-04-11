package com.example.app17.pagedmovielist

import com.example.app17.api.retrofit
import com.example.app17.models.Movie
import kotlinx.coroutines.delay

class MoviePagedListRepository {
    suspend fun getTopList(page: Int): List<Movie> {
        delay(2000)
        return retrofit.topList(page).films
    }
}
