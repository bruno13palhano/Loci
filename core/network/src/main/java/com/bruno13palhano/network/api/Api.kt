package com.bruno13palhano.network.api

import retrofit2.Response
import retrofit2.http.POST

internal interface Api {
    @POST("api/v1/login")
    suspend fun login(): Response<Unit>
}