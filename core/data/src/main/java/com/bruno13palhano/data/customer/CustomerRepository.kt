package com.bruno13palhano.data.customer

import com.bruno13palhano.model.Contract
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    suspend fun createContract(
        contract: Contract,
        onError: (error: Int) -> Unit,
        onSuccess: (id: Long) -> Unit
    )

    suspend fun updateContract(
        contract: Contract,
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
    ): Flow<Contract>

    fun getContracts(onError: (error: Int) -> Unit): Flow<List<Contract>>
}