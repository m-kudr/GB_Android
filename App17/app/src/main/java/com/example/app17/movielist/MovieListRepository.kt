package com.example.app17.movielist

import com.example.app17.api.retrofit
import com.example.app17.models.Movie
import kotlinx.coroutines.delay

class MovieListRepository {
    suspend fun getPremieres(year: Int, month: String): List<Movie> {
        return retrofit.movies(year, month).items
    }
}
