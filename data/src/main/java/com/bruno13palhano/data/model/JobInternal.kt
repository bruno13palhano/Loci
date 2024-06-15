package com.bruno13palhano.data.model

import com.bruno13palhano.model.JobData

data class JobInternal(
    val id: Long,
    val title: String,
    val description: String,
    val price: Float,
    val startDate: Long,
    val endDate: Long
)

internal fun JobInternal.asExternal() =
    JobData(
        id = id,
        title = title,
        description = description,
        price = price,
        startDate = startDate,
        endDate = endDate
    )

internal fun JobData.asInternal() =
    JobInternal(
        id = id,
        title = title,
        description = description,
        price = price,
        startDate = startDate,
        endDate = endDate
    )