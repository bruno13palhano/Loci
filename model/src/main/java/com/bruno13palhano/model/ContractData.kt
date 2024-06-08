package com.bruno13palhano.model

data class ContractData(
    val id: Long,
    val serviceId: Long,
    val serviceName: String,
    val serviceDescription: String,
    val customerName: String,
    val freelancerName: String,
    val price: Float,
    val status: String
)