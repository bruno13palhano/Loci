package com.bruno13palhano.network.api

import com.bruno13palhano.network.api.model.WorkspaceNet
import retrofit2.http.GET
import retrofit2.http.Path

internal interface WorkspaceApi {
    @GET("api/v1/workspace/{userUid}")
    suspend fun getWorkspace(@Path("userUid") userUid: String): List<WorkspaceNet>
}