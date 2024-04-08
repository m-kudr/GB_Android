package com.example.app16.domain

import com.example.app16.data.UsefulActivitiesRepository
import com.example.app16.data.UsefulActivityDto
import javax.inject.Inject

class GetUsefulActivityUseCase @Inject constructor(
    private val usefulActivitiesRepository: UsefulActivitiesRepository
) {
    suspend fun execute(): UsefulActivityDto {
        return usefulActivitiesRepository.getUsefulActivity()
    }
}