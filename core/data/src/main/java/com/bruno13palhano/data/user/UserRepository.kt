package com.bruno13palhano.data.user

interface UserRepository {
    suspend fun login(email: String, password: String)

    fun authenticated(): Boolean
}