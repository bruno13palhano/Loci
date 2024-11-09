package com.bruno13palhano.data.local

import kotlinx.coroutines.flow.Flow

internal interface MessagesLocalDataSource {
    suspend fun insert(message: String)

    fun get(message: String): Flow<String>
}