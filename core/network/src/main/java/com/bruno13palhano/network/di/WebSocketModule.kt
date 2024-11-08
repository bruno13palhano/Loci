package com.bruno13palhano.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.hildan.krossbow.stomp.StompClient
import org.hildan.krossbow.websocket.okhttp.OkHttpWebSocketClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object WebSocketModule {
    @Singleton
    @Provides
    fun providesOkHttpClient() = OkHttpClient()

    @Singleton
    @Provides
    fun providesStompClient(okHttpClient: OkHttpClient): StompClient {
        return StompClient(OkHttpWebSocketClient(okHttpClient))
    }
}