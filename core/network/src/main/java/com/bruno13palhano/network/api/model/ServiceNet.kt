package com.bruno13palhano.network.api.model

import com.bruno13palhano.model.Service
import com.squareup.moshi.Json

internal data class ServiceInternal(
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "price") val price: Float,
    @Json(name = "images") val images: List<String>
)

internal fun ServiceInternal.asExternal() =
    Service(
        id = id,
        title = title,
        description = description,
        price = price,
        images = images
    )

internal fun Service.asInternal() =
    ServiceInternal(
        id = id,
        title = title,
        description = description,
        price = price,
        images = images
    )