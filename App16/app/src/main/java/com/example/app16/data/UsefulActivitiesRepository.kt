package com.example.app16.data

import kotlinx.coroutines.delay
import com.example.app16.api.RetrofitInst
import javax.inject.Inject

class UsefulActivitiesRepository @Inject constructor() {
    suspend fun getUsefulActivity(): UsefulActivityDto {
        delay(2000)
        return RetrofitInst.searchUsefulActivityDto.getUsefulActivity()
    }
}