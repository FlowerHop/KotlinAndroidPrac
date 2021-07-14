package com.flowerhop.kotlinandroidprac.network

import com.flowerhop.kotlinandroidprac.network.entity.Envelope
import com.flowerhop.kotlinandroidprac.network.entity.User
import retrofit2.Response
import retrofit2.http.*

interface UserApi {
    companion object {
        const val PATH_ARG_USER_ID = "user_id"
    }
    @GET("users")
    suspend fun listUser(): Response<Envelope<List<User>>>

    @GET("users/{$PATH_ARG_USER_ID}")
    suspend fun getUser(@Path(PATH_ARG_USER_ID) userID: Int): Response<Envelope<User>>

    @FormUrlEncoded
    @POST("users")
    suspend fun createUser(@Field("name") name: String, @Field("job") job: String): Response<User>
}