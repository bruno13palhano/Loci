package com.bruno13palhano.model

data class Freelancer(
    override val uid: String,
    override val name: String,
    override val email: String,
    override val password: String,
    override val phone: String,
    override val address: String,
    override val city: String,
    override val timestamp: Long,
    val profession: List<String>,
    val experience: String,
    val service: List<Service>,
    val contracts: List<Contract>
) : User(
        uid = uid,
        name = name,
        email = email,
        password = password,
        phone = phone,
        address = address,
        city = city,
        timestamp = timestamp
    )