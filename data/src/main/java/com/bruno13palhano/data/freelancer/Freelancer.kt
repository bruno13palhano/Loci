package com.bruno13palhano.data.freelancer

import com.bruno13palhano.data.actors.Service
import kotlinx.coroutines.flow.Flow

internal interface Freelancer {
    suspend fun createProfessions(
        professions: List<String>,
        onError: (error: Int) -> Unit,
        onSuccess: () -> Unit
    )

    suspend fun updateProfessions(
        professions: List<String>,
        onError: (error: Int) -> Unit,
        onSuccess: () -> Unit
    )

    suspend fun createService(
        service: Service,
        onError: (error: Int) -> Unit,
        onSuccess: (id: Long) -> Unit
    )

    suspend fun updateService(
        service: Service,
        onError: (error: Int) -> Unit,
        onSuccess: () -> Unit
    )

    suspend fun removeService(
        serviceId: Long,
        onError: (error: Int) -> Unit,
        onSuccess: () -> Unit
    )

    suspend fun hideService(
        serviceId: Long,
        onError: (error: Int) -> Unit,
        onSuccess: () -> Unit
    )

    fun getServiceById(
        serviceId: Long,
        onError: (error: Int) -> Unit
    ): Flow<Service>

    fun getServices(onError: (error: Int) -> Unit): Flow<List<Service>>

    fun cancelContract(
        contractId: Long,
        onError: (error: Int) -> Unit,
        onSuccess: () -> Unit
    )
}