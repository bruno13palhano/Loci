package com.bruno13palhano.network.api.customer

import com.bruno13palhano.model.Contract
import com.bruno13palhano.model.Job

internal class RemoteCustomerDataSource : RemoteCustomer {
    override suspend fun insertJob(job: Job) {
        TODO("Not yet implemented")
    }

    override suspend fun updateJob(job: Job) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteJob(contractId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun getJobs(): List<Job> {
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