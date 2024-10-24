package com.bruno13palhano.forgotpassword.ui.presenter

import com.bruno13palhano.ui.shared.Reducer

internal class ForgotPasswordReducer : Reducer<ForgotPasswordState, ForgotPasswordEvent, ForgotPasswordEffect> {
    override fun reduce(
        previousState: ForgotPasswordState,
        event: ForgotPasswordEvent
    ): Pair<ForgotPasswordState, ForgotPasswordEffect?> {
        return when (event) {
            is ForgotPasswordEvent.VerifyEmail -> verifyEmail(previousState = previousState)

            is ForgotPasswordEvent.UpdatePassword -> updatePassword(previousState = previousState)

            is ForgotPasswordEvent.CancelUpdatePassword -> {
                cancelUpdatePassword(previousState = previousState)
            }

            is ForgotPasswordEvent.TogglePasswordVisibility -> {
                togglePasswordVisibility(previousState = previousState)
            }

            is ForgotPasswordEvent.ToggleConfirmPasswordVisibility -> {
                toggleConfirmPasswordVisibility(previousState = previousState)
            }

            is ForgotPasswordEvent.InternalError -> {
                internalError(previousState = previousState, errorType = event.errorType)
            }

            is ForgotPasswordEvent.NavigateToHome -> navigateToHome(previousState = previousState)

            is ForgotPasswordEvent.NavigateBack -> navigateBack(previousState = previousState)
        }
    }

    private fun verifyEmail(
        previousState: ForgotPasswordState
    ): Pair<ForgotPasswordState, ForgotPasswordEffect?> {
        return if (previousState.forgotPasswordFields.isEmailValid()) {
            previousState.copy(
                loading = true,
                invalidFields = false,
                showErrorInfo = false
            ) to null
        } else {
            previousState.copy(
                loading = false,
                invalidFields = true,
                showErrorInfo = true
            ) to ForgotPasswordEffect.ShowErrorInfo(errorType = ErrorType.InvalidEmail)
        }
    }

    private fun updatePassword(
        previousState: ForgotPasswordState
    ): Pair<ForgotPasswordState, ForgotPasswordEffect?> {
        return if (previousState.forgotPasswordFields.isPasswordValid()) {
            previousState.copy(
                loading = true,
                invalidFields = false,
                showErrorInfo = false
            ) to ForgotPasswordEffect.NavigateToHome
        } else {
            previousState.copy(
                loading = false,
                invalidFields = true,
                showErrorInfo = true
            ) to ForgotPasswordEffect.ShowErrorInfo(errorType = ErrorType.PasswordDoesNotMatch)
        }
    }

    private fun cancelUpdatePassword(
        previousState: ForgotPasswordState
    ): Pair<ForgotPasswordState, ForgotPasswordEffect?> {
        return previousState.copy(
            emailVerified = false
        ) to null
    }

    private fun togglePasswordVisibility(
        previousState: ForgotPasswordState
    ): Pair<ForgotPasswordState, ForgotPasswordEffect?> {
        return previousState.copy(
            passwordVisible = !previousState.passwordVisible
        ) to null
    }

    private fun toggleConfirmPasswordVisibility(
        previousState: ForgotPasswordState
    ): Pair<ForgotPasswordState, ForgotPasswordEffect?> {
        return previousState.copy(
            confirmPasswordVisible = !previousState.confirmPasswordVisible
        ) to null
    }

    private fun internalError(
        previousState: ForgotPasswordState,
        errorType: ErrorType
    ): Pair<ForgotPasswordState, ForgotPasswordEffect?> {
        return previousState.copy(
            loading = false,
            internalError = true,
            showErrorInfo = true
        ) to ForgotPasswordEffect.ShowErrorInfo(errorType = errorType)
    }

    private fun navigateToHome(
        previousState: ForgotPasswordState
    ): Pair<ForgotPasswordState, ForgotPasswordEffect?> {
        return previousState.copy(
            loading = false,
            internalError = false
        ) to ForgotPasswordEffect.NavigateToHome
    }

    private fun navigateBack(
        previousState: ForgotPasswordState
    ): Pair<ForgotPasswordState, ForgotPasswordEffect?> {
        return previousState.copy(
            loading = false,
            internalError = false
        ) to ForgotPasswordEffect.NavigateBack
    }
}