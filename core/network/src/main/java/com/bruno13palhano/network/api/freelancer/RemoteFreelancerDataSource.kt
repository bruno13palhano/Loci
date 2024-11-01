package com.bruno13palhano.network.api.freelancer

import com.bruno13palhano.model.Contract
import com.bruno13palhano.model.Service

internal class RemoteFreelancerDataSource : RemoteFreelancer {
    override suspend fun insertService(service: Service) {
        TODO("Not yet implemented")
    }

    override suspend fun updateService(service: Service) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteService(serviceId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun getServices(): List<Service> {
        TODO("Not yet implemented")
    }

    override suspend fun insertContract(contract: Contract) {
        TODO("Not yet implemented")
    }

    override suspend fun cancelContract(contractId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun getContracts(): List<Contract> {
        TODO("Not yet implemented")
    }
}