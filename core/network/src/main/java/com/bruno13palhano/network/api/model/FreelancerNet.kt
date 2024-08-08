package com.bruno13palhano.network.api.model

import com.bruno13palhano.model.Freelancer
import com.squareup.moshi.Json

internal data class FreelancerInternal(
    @Json(name = "uid") val uid: String,
    @Json(name = "name") val name: String,
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String,
    @Json(name = "phone") val phone: String,
    @Json(name = "address") val address: String,
    @Json(name = "city") val city: String,
    @Json(name = "profession") val profession: List<String>,
    @Json(name = "experience") val experience: String,
    @Json(name = "service") val service: List<ServiceInternal>,
    @Json(name = "contracts") val contracts: List<ContractInternal>
)

internal fun FreelancerInternal.asExternal() =
    Freelancer(
        uid = uid,
        name = name,
        email = email,
        password = password,
        phone = phone,
        address = address,
        city = city,
        profession = profession,
        experience = experience,
        service = service.map { it.asExternal() },
        contracts = contracts.map { it.asExternal() }
    )

internal fun Freelancer.asInternal() =
    FreelancerInternal(
        uid = uid,
        name = name,
        email = email,
        password = password,
        phone = phone,
        address = address,
        city = city,
        profession = profession,
        experience = experience,
        service = service.map { it.asInternal() },
        contracts = contracts.map { it.asInternal() }
    )