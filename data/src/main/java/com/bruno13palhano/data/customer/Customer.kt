package com.bruno13palhano.data.customer

import com.bruno13palhano.model.ContractData
import kotlinx.coroutines.flow.Flow

interface Customer {
    suspend fun createContract(
        contract: ContractData,
        onError: (error: Int) -> Unit,
        onSuccess: (id: Long) -> Unit
    )

    suspend fun updateContract(
        contract: ContractData,
        onError: (error: Int) -> Unit,
        onSuccess: () -> Unit
    )

    suspend fun cancelContract(
        id: Long,
        onError: (error: Int) -> Unit,
        onSuccess: () -> Unit
    )

    fun getContractById(
        id: Long,
        onError: (error: Int) -> Unit
    ): Flow<ContractData>

    fun getContracts(onError: (error: Int) -> Unit): Flow<List<ContractData>>
}