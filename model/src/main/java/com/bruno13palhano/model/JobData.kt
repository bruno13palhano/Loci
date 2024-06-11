package com.bruno13palhano.model

data class JobData(
    val id: Long,
    val title: String,
    val description: String,
    val price: Float,
    val startDate: Long,
    val endDate: Long
)