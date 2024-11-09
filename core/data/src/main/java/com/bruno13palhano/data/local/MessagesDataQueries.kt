package com.bruno13palhano.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOne
import cache.MessagesQueries
import com.bruno13palhano.data.di.Dispatcher
import com.bruno13palhano.data.di.LociDispatchers.IO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

internal class MessagesDataQueries @Inject constructor(
    private val queries: MessagesQueries,
    @Dispatcher(IO) private val dispatcher: CoroutineDispatcher
) : MessagesLocalDataSource {
    override suspend fun insert(message: String) {
        queries.insert(message = message)
    }

    override fun get(message: String): Flow<String> {
        return queries.get(message = message)
            .asFlow()
            .mapToOne(dispatcher)
            .catch { it.printStackTrace() }
    }
}