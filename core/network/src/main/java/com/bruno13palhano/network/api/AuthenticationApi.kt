package com.bruno13palhano.network.api

import com.bruno13palhano.network.api.model.UserNet
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

internal interface AuthenticationApi {
    @POST("api/v1/login")
    suspend fun login(@Body user: UserNet): Response<Unit>

    @POST("api/v1/register")
    suspend fun register(@Body user: UserNet): Response<Unit>

    @PUT("api/v1/update")
    suspend fun update(@Body user: UserNet): Response<Unit>

    @PUT("api/v1/update/password")
    suspend fun updatePassword(@Body user: UserNet): Response<Unit>

    @GET("api/v1/user/{email}")
    suspend fun getUserByEmail(@Path("email") username: String): UserNet

    @GET("api/v1/user/{uid}")
    suspend fun getUserByUid(@Path("uid") uid: String): UserNet

    @POST("api/v1/authenticated")
    suspend fun isAuthenticated(
        @Body token: String
    ): Boolean
}