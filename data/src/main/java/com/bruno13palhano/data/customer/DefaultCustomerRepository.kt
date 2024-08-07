package com.bruno13palhano.data.customer

import com.bruno13palhano.model.ContractData
import kotlinx.coroutines.flow.Flow

internal class DefaultCustomer : Customer {
    override suspend fun createContract(
        contract: ContractData,
        onError: (error: Int) -> Unit,
        onSuccess: (id: Long) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun updateContract(
        contract: ContractData,
        onError: (error: Int) -> Unit,
        onSuccess: () -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun cancelContract(
        id: Long,
        onError: (error: Int) -> Unit,
        onSuccess: () -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getContractById(
        id: Long,
        onError: (error: Int) -> Unit
    ): Flow<ContractData> {
        TODO("Not yet implemented")
    }

    override fun getContracts(onError: (error: Int) -> Unit): Flow<List<ContractData>> {
        TODO("Not yet implemented")
    }
}