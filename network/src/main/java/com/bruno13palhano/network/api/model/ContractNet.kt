package com.bruno13palhano.network.api.model

import com.bruno13palhano.model.Contract
import com.squareup.moshi.Json

internal data class ContractInternal(
    @Json(name = "id") val id: Long,
    @Json(name = "serviceId") val serviceId: Long,
    @Json(name = "serviceTitle") val serviceTitle: String,
    @Json(name = "serviceDescription") val serviceDescription: String,
    @Json(name = "customerName") val customerName: String,
    @Json(name = "freelancerName") val freelancerName: String,
    @Json(name = "price") val price: Float,
    @Json(name = "status") val status: String
)

internal fun ContractInternal.asExternal() =
    Contract(
        id = id,
        serviceId = serviceId,
        serviceTitle = serviceTitle,
        serviceDescription = serviceDescription,
        customerName = customerName,
        freelancerName = freelancerName,
        price = price,
        status = status
    )

internal fun Contract.asInternal() =
    ContractInternal(
        id = id,
        serviceId = serviceId,
        serviceTitle = serviceTitle,
        serviceDescription = serviceDescription,
        customerName = customerName,
        freelancerName = freelancerName,
        price = price,
        status = status
    )