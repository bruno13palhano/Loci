package com.bruno13palhano.network.api.messages

import com.bruno13palhano.network.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.hildan.krossbow.stomp.StompClient
import org.hildan.krossbow.stomp.sendText
import org.hildan.krossbow.stomp.subscribeText
import javax.inject.Inject

internal class RemoteMessagesDataSource @Inject constructor(
    private val stompClient: StompClient
) : RemoteMessages {
    override suspend fun sendMessage(message: String) {
        try {
            val stompSession = stompClient.connect(url = BuildConfig.apiWs)
            stompSession.sendText(destination = BuildConfig.apiWsSendDestination, body = message)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getMessage(): String {
        var message = ""
        return try {
            val stompSession = stompClient.connect(url = BuildConfig.apiWs)
            val subscription = stompSession.subscribeText(
                destination = BuildConfig.apiWsSubscriptionDestination
            )

            coroutineScope {
                val job = launch(Dispatchers.IO) {
                    subscription.collect {
                        message = it
                    }
                }

                delay(3000)
                job.cancel()
                stompSession.disconnect()
            }
            message
        } catch (e: Exception) {
            e.printStackTrace()
            message
        }
    }
}