package com.bruno13palhano.data.model

import com.bruno13palhano.model.Service

internal data class ServiceInternal(
    val id: Long,
    val title: String,
    val description: String,
    val price: Float,
    val images: List<String>,
    val timestamp: Long
)

internal fun ServiceInternal.asExternal() =
    Service(
        id = id,
        title = title,
        description = description,
        price = price,
        images = images,
        timestamp = timestamp
    )

internal fun Service.asInternal() =
    ServiceInternal(
        id = id,
        title = title,
        description = description,
        price = price,
        images = images,
        timestamp = timestamp
    )