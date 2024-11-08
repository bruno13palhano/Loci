package com.bruno13palhano.data.di

import com.bruno13palhano.data.messages.DefaultMessagesRepository
import com.bruno13palhano.data.messages.MessagesRepository
import com.bruno13palhano.data.user.DefaultUserRepository
import com.bruno13palhano.data.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class UserRep

@Qualifier
annotation class MessageRep

@InstallIn(SingletonComponent::class)
@Module
internal abstract class RepositoryModule {
    @UserRep
    @Singleton
    @Binds
    abstract fun bindUserRepository(repository: DefaultUserRepository): UserRepository

    @MessageRep
    @Singleton
    @Binds
    abstract fun bindMessagesRepository(repository: DefaultMessagesRepository): MessagesRepository
}