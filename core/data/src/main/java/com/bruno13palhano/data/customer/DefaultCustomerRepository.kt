package com.bruno13palhano.data.customer

import com.bruno13palhano.model.Contract
import kotlinx.coroutines.flow.Flow

internal class DefaultCustomerRepository : CustomerRepository {
    override suspend fun createContract(
        contract: Contract,
        onError: (error: Int) -> Unit,
        onSuccess: (id: Long) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun updateContract(
        contract: Contract,
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
    ): Flow<Contract> {
        TODO("Not yet implemented")
    }

    override fun getContracts(onError: (error: Int) -> Unit): Flow<List<Contract>> {
        TODO("Not yet implemented")
    }
}