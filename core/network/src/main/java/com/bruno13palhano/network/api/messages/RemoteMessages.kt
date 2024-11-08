package com.bruno13palhano.network.api.messages

interface RemoteMessages {
    suspend fun sendMessage(message: String)

    suspend fun getMessage(): String
}