package com.bruno13palhano.network.api.freelancer

import com.bruno13palhano.model.Contract
import com.bruno13palhano.model.Service

interface RemoteFreelancer {
    suspend fun insertService(service: Service)

    suspend fun updateService(service: Service)

    suspend fun deleteService(serviceId: Long)

    suspend fun getServices(): List<Service>

    suspend fun insertContract(contract: Contract)

    suspend fun cancelContract(contractId: Long)

    suspend fun getContracts(): List<Contract>
}