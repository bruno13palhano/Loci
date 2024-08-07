package com.bruno13palhano.network.api.customer

import com.bruno13palhano.model.ContractData
import com.bruno13palhano.model.JobData

interface RemoteCustomer {
    suspend fun insertJob(job: JobData)

    suspend fun updateJob(job: JobData)

    suspend fun deleteJob(contractId: Long)

    suspend fun getJobs(): List<JobData>

    suspend fun insertContract(contract: ContractData)

    suspend fun cancelContract(contractId: Long)

    suspend fun getContracts(): List<ContractData>
}