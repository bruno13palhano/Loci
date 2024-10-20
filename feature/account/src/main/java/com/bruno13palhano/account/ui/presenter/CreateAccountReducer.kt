package com.bruno13palhano.account.ui.presenter

import com.bruno13palhano.ui.shared.Reducer

internal class CreateAccountReducer : Reducer<CreateAccountState, CreateAccountEvent, CreateAccountEffect> {
    override fun reduce(
        previousState: CreateAccountState,
        event: CreateAccountEvent
    ): Pair<CreateAccountState, CreateAccountEffect?> {
        return when (event) {
            is CreateAccountEvent.Done -> {
                previousState.copy(loading = true, error = false) to null
            }

            is CreateAccountEvent.Error -> {
                previousState.copy(loading = false, error = true) to CreateAccountEffect.ShowError
            }

            is CreateAccountEvent.TogglePasswordVisibility -> {
                previousState.copy(passwordVisible = !previousState.passwordVisible) to null
            }

            is CreateAccountEvent.ToggleConfirmPasswordVisibility -> {
                previousState.copy(
                    confirmPasswordVisible = !previousState.confirmPasswordVisible
                ) to null
            }

            is CreateAccountEvent.NavigateToHome -> {
                previousState.copy(
                    loading = false,
                    error = false
                ) to CreateAccountEffect.NavigateToHome
            }

            is CreateAccountEvent.NavigateBack -> {
                previousState.copy(
                    loading = false,
                    error = false
                ) to CreateAccountEffect.NavigateBack
            }
        }
    }
}