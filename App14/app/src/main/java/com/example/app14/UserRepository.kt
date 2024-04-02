package com.example.app14

import kotlinx.coroutines.delay

class UserRepository {

    suspend fun loadUser(): UsersModels? {
        delay(2000)
        return retrofit()
    }

    private suspend fun retrofit(): UsersModels? {
        return RetrofitServices.searchUserApi.getUserInfo().body()
    }
}
