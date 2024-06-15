package com.bruno13palhano.network.api.model

import com.bruno13palhano.model.ContractData
import com.squareup.moshi.Json

internal data class ContractInternal(
    @Json(name = "id") val id: Long,
    @Json(name = "serviceId") val serviceId: Long,
    @Json(name = "serviceName") val serviceName: String,
    @Json(name = "serviceDescription") val serviceDescription: String,
    @Json(name = "customerName") val customerName: String,
    @Json(name = "freelancerName") val freelancerName: String,
    @Json(name = "price") val price: Float,
    @Json(name = "status") val status: String
)

internal fun ContractInternal.asExternal() =
    ContractData(
        id = id,
        serviceId = serviceId,
        serviceName = serviceName,
        serviceDescription = serviceDescription,
        customerName = customerName,
        freelancerName = freelancerName,
        price = price,
        status = status
    )

internal fun ContractData.asInternal() =
    ContractInternal(
        id = id,
        serviceId = serviceId,
        serviceName = serviceName,
        serviceDescription = serviceDescription,
        customerName = customerName,
        freelancerName = freelancerName,
        price = price,
        status = status
    )