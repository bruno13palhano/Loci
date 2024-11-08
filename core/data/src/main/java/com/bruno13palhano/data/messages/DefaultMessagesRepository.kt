package com.bruno13palhano.data.messages

import com.bruno13palhano.network.api.messages.RemoteMessages
import javax.inject.Inject

internal class DefaultMessagesRepository @Inject constructor(
    private val messagesRemote: RemoteMessages
) : MessagesRepository {
    override suspend fun sendMessage(message: String) {
        messagesRemote.sendMessage(message = message)
    }

    override suspend fun getMessage(): String {
        return messagesRemote.getMessage()
    }
}