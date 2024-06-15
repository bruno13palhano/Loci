package com.bruno13palhano.network.api.model

import com.bruno13palhano.model.JobData
import com.squareup.moshi.Json

internal data class JobInternal(
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "price") val price: Float,
    @Json(name = "startDate") val startDate: Long,
    @Json(name = "endDate") val endDate: Long
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