package com.bruno13palhano.data.model

import com.bruno13palhano.model.ServiceData

internal data class InternalService(
    val id: Long,
    val name: String,
    val description: String,
    val price: Float,
    val images: List<String>
)

internal fun InternalService.asExternal() =
    ServiceData(
        id = id,
        name = name,
        description = description,
        price = price,
        images = images
    )

internal fun ServiceData.asInternal() =
    InternalService(
        id = id,
        name = name,
        description = description,
        price = price,
        images = images
    )