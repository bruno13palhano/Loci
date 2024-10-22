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

            is ForgotPasswordEvent.Error -> error(previousState = previousState)

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
                error = false
            ) to null
        } else {
            previousState.copy(
                loading = false,
                error = true
            ) to ForgotPasswordEffect.ShowError
        }
    }

    private fun updatePassword(
        previousState: ForgotPasswordState
    ): Pair<ForgotPasswordState, ForgotPasswordEffect?> {
        return if (previousState.forgotPasswordFields.isPasswordValid()) {
            previousState.copy(
                loading = true,
                error = false
            ) to ForgotPasswordEffect.NavigateToHome
        } else {
            previousState.copy(
                loading = false,
                error = true
            ) to ForgotPasswordEffect.ShowError
        }
    }

    private fun error(
        previousState: ForgotPasswordState
    ): Pair<ForgotPasswordState, ForgotPasswordEffect?> {
        return previousState.copy(
            loading = false,
            error = true
        ) to ForgotPasswordEffect.ShowError
    }

    private fun navigateToHome(
        previousState: ForgotPasswordState
    ): Pair<ForgotPasswordState, ForgotPasswordEffect?> {
        return previousState.copy(
            loading = false,
            error = false
        ) to ForgotPasswordEffect.NavigateToHome
    }

    private fun navigateBack(
        previousState: ForgotPasswordState
    ): Pair<ForgotPasswordState, ForgotPasswordEffect?> {
        return previousState.copy(
            loading = false,
            error = false
        ) to ForgotPasswordEffect.NavigateBack
    }
}