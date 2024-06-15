package com.bruno13palhano.network.api.model

import com.bruno13palhano.model.CustomerData
import com.squareup.moshi.Json

internal data class CustomerInternal(
    @Json(name = "uid") val uid: String,
    @Json(name = "name") val name: String,
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String,
    @Json(name = "phone") val phone: String,
    @Json(name = "address") val address: String,
    @Json(name = "city") val city: String,
    @Json(name = "jobs") val jobs: List<JobInternal>,
    @Json(name = "contracts") val contracts: List<ContractInternal>
)

internal fun CustomerInternal.asCustomer() =
    CustomerData(
        uid = uid,
        name = name,
        email = email,
        password = password,
        phone = phone,
        address = address,
        city = city,
        jobs = jobs.map { it.asExternal() },
        contracts = contracts.map { it.asExternal() }
    )

internal fun CustomerData.asInternal() =
    CustomerInternal(
        uid = uid,
        name = name,
        email = email,
        password = password,
        phone = phone,
        address = address,
        city = city,
        jobs = jobs.map { it.asInternal() },
        contracts = contracts.map { it.asInternal() }
    )