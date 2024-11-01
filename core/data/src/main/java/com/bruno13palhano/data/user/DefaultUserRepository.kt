package com.bruno13palhano.data.user

import com.bruno13palhano.data.di.DefaultSessionManager
import javax.inject.Inject

internal class DefaultUserRepository @Inject constructor(
    @DefaultSessionManager private val sessionManager: SessionManager
) : UserRepository {
    override suspend fun login(email: String, password: String) {
        sessionManager.saveAuthToken(token = email)
    }

    override fun authenticated(): Boolean {
        return sessionManager.fetchAuthToken() != null
    }
}