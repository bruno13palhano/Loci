package com.bruno13palhano.model

data class FreelancerData(
    override val uid: String,
    override val name: String,
    override val email: String,
    override val password: String,
    override val phone: String,
    override val address: String,
    override val city: String,
    val profession: List<String>,
    val experience: String,
    val service: List<ServiceData>,
    val contracts: List<ContractData>
) : UserData(
        uid = uid,
        name = name,
        email = email,
        password = password,
        phone = phone,
        address = address,
        city = city
    )