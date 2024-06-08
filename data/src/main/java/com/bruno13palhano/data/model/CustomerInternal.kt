package com.bruno13palhano.data.model

import com.bruno13palhano.model.CustomerData

internal data class CustomerInternal(
    override val uid: String,
    override val name: String,
    override val email: String,
    override val password: String,
    override val phone: String,
    override val address: String,
    override val city: String,
    val contracts: List<ContractInternal>
) : UserInternal(
        uid = uid,
        name = name,
        email = email,
        password = password,
        phone = phone,
        address = address,
        city = city
    )

internal fun CustomerInternal.asExternal() =
    CustomerData(
        uid = uid,
        name = name,
        email = email,
        password = password,
        phone = phone,
        address = address,
        city = city,
        contracts = contracts.map { it.asExternal() }
    )