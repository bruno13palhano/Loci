package com.bruno13palhano.model

open class User(
    open val uid: String,
    open val name: String,
    open val email: String,
    open val password: String,
    open val phone: String,
    open val address: String,
    open val city: String
)