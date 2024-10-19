package com.bruno13palhano.account.ui.presenter

import com.bruno13palhano.ui.shared.Reducer

internal class CreateAccountReducer : Reducer<CreateAccountState, CreateAccountEvent, CreateAccountEffect> {
    override fun reduce(
        previousState: CreateAccountState,
        event: CreateAccountEvent
    ): Pair<CreateAccountState, CreateAccountEffect?> {
        return when (event) {
            CreateAccountEvent.Done -> {
                previousState.copy(loading = true, error = false) to null
            }

            CreateAccountEvent.Error -> {
                previousState.copy(loading = false, error = true) to CreateAccountEffect.ShowError
            }

            CreateAccountEvent.TogglePasswordVisibility -> {
                previousState.copy(passwordVisible = !previousState.passwordVisible) to null
            }

            CreateAccountEvent.ToggleConfirmPasswordVisibility -> {
                previousState.copy(
                    confirmPasswordVisible = !previousState.confirmPasswordVisible
                ) to null
            }

            CreateAccountEvent.NavigateToHome -> {
                previousState.copy(
                    loading = false,
                    error = false
                ) to CreateAccountEffect.NavigateToHome
            }
        }
    }
}