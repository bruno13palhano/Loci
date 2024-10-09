package com.bruno13palhano.network.di

import com.bruno13palhano.network.BuildConfig
import com.bruno13palhano.network.api.AuthenticationApi
import com.bruno13palhano.network.api.WorkspaceApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = BuildConfig.apiUrl

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        val moshi =
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client =
            OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .client(client)
                .build()
    }

    @Provides
    @Singleton
    fun provideAuthenticationApi(retrofit: Retrofit): AuthenticationApi {
        val authenticationApi: AuthenticationApi by lazy { retrofit.create(AuthenticationApi::class.java) }

        return authenticationApi
    }

    @Provides
    @Singleton
    fun provideWorkspaceApi(retrofit: Retrofit): WorkspaceApi {
        val workspaceApi: WorkspaceApi by lazy { retrofit.create(WorkspaceApi::class.java) }

        return workspaceApi
    }
}