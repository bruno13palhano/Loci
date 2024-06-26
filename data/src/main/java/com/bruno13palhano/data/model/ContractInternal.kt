package com.bruno13palhano.data.model

import com.bruno13palhano.model.ContractData

internal data class ContractInternal(
    val id: Long,
    val serviceId: Long,
    val serviceName: String,
    val serviceDescription: String,
    val customerName: String,
    val freelancerName: String,
    val price: Float,
    val status: String
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