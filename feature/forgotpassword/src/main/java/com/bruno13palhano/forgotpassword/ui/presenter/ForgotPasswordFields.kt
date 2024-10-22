package com.bruno13palhano.forgotpassword.ui.presenter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

internal class ForgotPasswordFields {
    var email by mutableStateOf("")
        private set
    var verificationCode by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set

    fun updateEmail(email: String) {
        this.email = email
    }

    fun updateVerificationCode(verificationCode: String) {
        this.verificationCode = verificationCode
    }

    fun updatePassword(password: String) {
        this.password = password
    }

    fun updateConfirmPassword(confirmPassword: String) {
        this.confirmPassword = confirmPassword
    }

    // TODO: Check email pattern with regex
    fun isEmailValid() = email.isNotBlank()

    fun isVerificationCodeValid() = verificationCode.isNotBlank() && verificationCode.length == 6

    // TODO: Impose some restrictions on password pattern with regex
    fun isPasswordValid(): Boolean {
        return password.isNotBlank() && password.length >= 8 && passwordMatch()
    }

    private fun passwordMatch(): Boolean {
        return password == confirmPassword
    }
}