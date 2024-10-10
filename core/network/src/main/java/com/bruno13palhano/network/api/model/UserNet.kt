package com.bruno13palhano.network.api.model

import com.bruno13palhano.model.User
import com.squareup.moshi.Json

data class UserNet(
    @Json(name = "uid") val uid: String,
    @Json(name = "name") val name: String,
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String,
    @Json(name = "phone") val phone: String,
    @Json(name = "address") val address: String,
    @Json(name = "city") val city: String,
    @Json(name = "timestamp") val timestamp: Long
)

internal fun UserNet.asExternal() = User(
    uid = uid,
    name = name,
    email = email,
    password = password,
    phone = phone,
    address = address,
    city = city,
    timestamp = timestamp
)

internal fun User.asInternal() = UserNet(
    uid = uid,
    name = name,
    email = email,
    password = password,
    phone = phone,
    address = address,
    city = city,
    timestamp = timestamp
)