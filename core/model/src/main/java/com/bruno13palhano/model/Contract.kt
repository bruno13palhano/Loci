package com.bruno13palhano.model

data class Contract(
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