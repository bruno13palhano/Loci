package com.bruno13palhano.model

data class Service(
    val id: Long,
    val title: String,
    val description: String,
    val price: Float,
    val images: List<String>,
    val timestamp: Long
)