package com.bruno13palhano.data.messages

interface MessagesRepository {
    suspend fun sendMessage(message: String)

    suspend fun getMessage(): String
}