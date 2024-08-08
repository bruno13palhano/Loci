package com.bruno13palhano.data.freelancer

import com.bruno13palhano.model.Service
import kotlinx.coroutines.flow.Flow

internal class DefaultFreelancerRepository : FreelancerRepository {
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
        service: Service,
        onError: (error: Int) -> Unit,
        onSuccess: (id: Long) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun updateService(
        service: Service,
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
    ): Flow<Service> {
        TODO("Not yet implemented")
    }

    override fun getServices(onError: (error: Int) -> Unit): Flow<List<Service>> {
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