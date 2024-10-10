package com.bruno13palhano.data.model

import com.bruno13palhano.model.User

internal open class UserInternal(
    open val uid: String,
    open val name: String,
    open val email: String,
    open val password: String,
    open val phone: String,
    open val address: String,
    open val city: String,
    open val timestamp: Long
)

internal fun UserInternal.asExternal() =
    User(
        uid = uid,
        name = name,
        email = email,
        password = password,
        phone = phone,
        address = address,
        city = city,
        timestamp = timestamp
    )

internal fun User.asInternal() =
    UserInternal(
        uid = uid,
        name = name,
        email = email,
        password = password,
        phone = phone,
        address = address,
        city = city,
        timestamp = timestamp
    )