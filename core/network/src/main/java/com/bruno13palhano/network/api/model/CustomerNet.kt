package com.bruno13palhano.network.api.model

import com.bruno13palhano.model.Customer
import com.squareup.moshi.Json

internal data class CustomerNet(
    @Json(name = "uid") val uid: String,
    @Json(name = "name") val name: String,
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String,
    @Json(name = "phone") val phone: String,
    @Json(name = "address") val address: String,
    @Json(name = "city") val city: String,
    @Json(name = "timestamp") val timestamp: Long,
    @Json(name = "jobs") val jobs: List<JobNet>,
    @Json(name = "contracts") val contracts: List<ContractNet>
)

internal fun CustomerNet.asCustomer() =
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
    CustomerNet(
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