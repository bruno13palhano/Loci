package com.bruno13palhano.network.api

import com.bruno13palhano.network.api.model.UserNet
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

internal interface Api {
    @POST("api/v1/login")
    suspend fun login(@Body user: UserNet): Response<Unit>

    @POST("api/v1/register")
    suspend fun register(@Body user: UserNet): Response<Unit>

}