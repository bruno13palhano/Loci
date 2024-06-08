package com.bruno13palhano.data.model

import com.bruno13palhano.model.UserData

internal open class UserInternal(
    open val uid: String,
    open val name: String,
    open val email: String,
    open val password: String,
    open val phone: String,
    open val address: String,
    open val city: String
)

internal fun UserInternal.asUser() =
    UserData(
        uid = uid,
        name = name,
        email = email,
        password = password,
        phone = phone,
        address = address,
        city = city
    )

internal fun UserData.asInternal() =
    UserInternal(
        uid = uid,
        name = name,
        email = email,
        password = password,
        phone = phone,
        address = address,
        city = city
    )