package com.bruno13palhano.data.di

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

@InstallIn(SingletonComponent::class)
@Module
internal abstract class RepositoryModule {
    @UserRep
    @Singleton
    @Binds
    abstract fun bindUserRepository(repository: DefaultUserRepository): UserRepository
}