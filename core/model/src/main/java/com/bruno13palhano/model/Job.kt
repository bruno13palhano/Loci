package com.bruno13palhano.model

data class Job(
    val id: Long,
    val title: String,
    val description: String,
    val price: Float,
    val startDate: Long,
    val endDate: Long,
    val timestamp: Long
)