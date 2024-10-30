package com.bruno13palhano.data.user

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

private const val TOKEN_FILE_NAME = "LOCI_TOKEN"

@Singleton
internal class SessionManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences(TOKEN_FILE_NAME, Context.MODE_PRIVATE)

    companion object {
        const val USER_AUTH_TOKEN = "USER_AUTH_TOKEN"
    }

    fun saveAuthToken(token: String) {
        val editor = preferences.edit()
        editor.putString(USER_AUTH_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return preferences.getString(USER_AUTH_TOKEN, null)
    }
}