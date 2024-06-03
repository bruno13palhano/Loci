package com.bruno13palhano.model

data class Service(
    val id: Long,
    val name: String,
    val description: String,
    val price: Float,
    val images: List<String>
)
