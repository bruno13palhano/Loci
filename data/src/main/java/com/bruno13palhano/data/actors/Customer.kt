package com.bruno13palhano.data.model

internal data class Customer(
    override val uid: String,
    override val name: String,
    override val email: String,
    override val password: String,
    override val phone: String,
    override val address: String,
    override val city: String,
    val internalContracts: List<InternalContract>
): User(
    uid = uid,
    name = name,
    email = email,
    password = password,
    phone = phone,
    address = address,
    city = city
)
