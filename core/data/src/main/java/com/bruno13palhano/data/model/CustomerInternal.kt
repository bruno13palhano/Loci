package com.bruno13palhano.data.model

import com.bruno13palhano.model.Customer

internal data class CustomerInternal(
    override val uid: String,
    override val name: String,
    override val email: String,
    override val password: String,
    override val phone: String,
    override val address: String,
    override val city: String,
    override val timestamp: Long,
    val jobs: List<JobInternal>,
    val contracts: List<ContractInternal>
) : UserInternal(
        uid = uid,
        name = name,
        email = email,
        password = password,
        phone = phone,
        address = address,
        city = city,
        timestamp = timestamp
    )

internal fun CustomerInternal.asExternal() =
    Customer(
        uid = uid,
        name = name,
        email = email,
        password = password,
        phone = phone,
        address = address,
        city = city,
        timestamp = timestamp,
        jobs = jobs.map { it.asExternal() },
        contracts = contracts.map { it.asExternal() }
    )

internal fun Customer.asInternal() =
    CustomerInternal(
        uid = uid,
        name = name,
        email = email,
        password = password,
        phone = phone,
        address = address,
        city = city,
        timestamp = timestamp,
        jobs = jobs.map { it.asInternal() },
        contracts = contracts.map { it.asInternal() }
    )