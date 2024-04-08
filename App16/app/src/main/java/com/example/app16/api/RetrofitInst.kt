package com.example.app16.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.example.app16.data.UsefulActivityDto

object RetrofitInst {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.boredapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val searchUsefulActivityDto: SearchUsefulActivityDto =
        retrofit.create(SearchUsefulActivityDto::class.java)
}

interface SearchUsefulActivityDto {
    @GET("/api/activity/")
    suspend fun getUsefulActivity(): UsefulActivityDto
}