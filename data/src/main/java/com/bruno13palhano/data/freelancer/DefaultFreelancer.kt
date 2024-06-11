package com.bruno13palhano.data.freelancer

import com.bruno13palhano.model.ServiceData
import kotlinx.coroutines.flow.Flow

internal class DefaultFreelancer : Freelancer {
    override suspend fun createProfessions(
        professions: List<String>,
        onError: (error: Int) -> Unit,
        onSuccess: () -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun updateProfessions(
        professions: List<String>,
        onError: (error: Int) -> Unit,
        onSuccess: () -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun createService(
        service: ServiceData,
        onError: (error: Int) -> Unit,
        onSuccess: (id: Long) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun updateService(
        service: ServiceData,
        onError: (error: Int) -> Unit,
        onSuccess: () -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun removeService(
        serviceId: Long,
        onError: (error: Int) -> Unit,
        onSuccess: () -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun hideService(
        serviceId: Long,
        onError: (error: Int) -> Unit,
        onSuccess: () -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getServiceById(
        serviceId: Long,
        onError: (error: Int) -> Unit
    ): Flow<ServiceData> {
        TODO("Not yet implemented")
    }

    override fun getServices(onError: (error: Int) -> Unit): Flow<List<ServiceData>> {
        TODO("Not yet implemented")
    }

    override fun cancelContract(
        contractId: Long,
        onError: (error: Int) -> Unit,
        onSuccess: () -> Unit
    ) {
        TODO("Not yet implemented")
    }
}