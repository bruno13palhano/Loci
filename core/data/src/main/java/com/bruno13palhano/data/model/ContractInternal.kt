package com.bruno13palhano.data.model

import com.bruno13palhano.model.Contract

internal data class ContractInternal(
    val id: Long,
    val serviceId: Long,
    val serviceTitle: String,
    val serviceDescription: String,
    val customerName: String,
    val freelancerName: String,
    val price: Float,
    val status: String,
    val timestamp: Long
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
        status = status,
        timestamp = timestamp
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
        status = status,
        timestamp = timestamp
    )