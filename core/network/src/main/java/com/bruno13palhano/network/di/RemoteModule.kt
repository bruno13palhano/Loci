package com.bruno13palhano.network.di

import com.bruno13palhano.network.api.messages.RemoteMessages
import com.bruno13palhano.network.api.messages.RemoteMessagesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class RemoteModule {
    @Singleton
    @Binds
    abstract fun bindMessagesRemote(remote: RemoteMessagesDataSource): RemoteMessages
}