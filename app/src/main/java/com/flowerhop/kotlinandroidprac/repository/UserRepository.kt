package com.flowerhop.kotlinandroidprac.repository

import com.flowerhop.kotlinandroidprac.network.ApiClient
import com.flowerhop.kotlinandroidprac.network.entity.User

class UserRepository(private val apiClient: ApiClient) {

    suspend fun listUser(): List<User> {
        return apiClient.listUser()
    }

    suspend fun getUser(userID: Int): User {
        return apiClient.getUser(userID)
    }
}