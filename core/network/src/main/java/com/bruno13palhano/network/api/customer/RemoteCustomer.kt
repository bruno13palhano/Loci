package com.bruno13palhano.network.api.customer

import com.bruno13palhano.model.Contract
import com.bruno13palhano.model.Job

interface RemoteCustomer {
    suspend fun insertJob(job: Job)

    suspend fun updateJob(job: Job)

    suspend fun deleteJob(contractId: Long)

    suspend fun getJobs(): List<Job>

    suspend fun insertContract(contract: Contract)

    suspend fun cancelContract(contractId: Long)

    suspend fun getContracts(): List<Contract>
}