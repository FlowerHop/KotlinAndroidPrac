package com.flowerhop.kotlinandroidprac.network

import android.util.Log
import com.flowerhop.kotlinandroidprac.network.entity.User
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private const val TAG = "AppClient"
    }
    private val okHttp: Call.Factory
    private val retrofit: Retrofit
    private val userApi: UserApi

    constructor(baseUrl: String = "https://reqres.in/api/") {
        this.okHttp = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }).build()
        this.retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(this.okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        this.userApi = retrofit.create(UserApi::class.java)
    }

    suspend fun getUser(userID: Int): User {
        val response = userApi.getUser(userID)
        try {
            return if (response.isSuccessful) {
                response.body()!!.data
            } else throw Exception("Can't get user which id = $userID.")
        } catch (e: Exception) {
            Log.e(TAG, "getUser failed: $e" )
            throw e
        }
    }

    suspend fun createUser(userName: String, job: String): User {
        val response = userApi.createUser(userName, job)
        try {
            return if (response.isSuccessful) {
                response.body()!!
            } else throw Exception("Can't create a user which userName = $userName and job = $job.")
        } catch (e: Exception) {
            Log.e(TAG, "createUser failed: $e" )
            throw e
        }
    }

    suspend fun listUser(): List<User> {
        val response = userApi.listUser()
        try {
            return if (response.isSuccessful) {
                return response.body()!!.data
            } else throw Exception("Can't get the user list.")
        } catch (e: Exception) {
            Log.e(TAG, "listUser failed: $e" )
            throw e
        }
    }
}