package com.bruno13palhano.model

data class ServiceData(
    val id: Long,
    val title: String,
    val description: String,
    val price: Float,
    val images: List<String>
)