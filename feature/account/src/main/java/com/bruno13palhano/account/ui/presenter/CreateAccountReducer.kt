package com.bruno13palhano.account.ui.presenter

import com.bruno13palhano.ui.shared.Reducer

internal class CreateAccountReducer : Reducer<CreateAccountState, CreateAccountEvent, CreateAccountEffect> {
    override fun reduce(
        previousState: CreateAccountState,
        event: CreateAccountEvent
    ): Pair<CreateAccountState, CreateAccountEffect?> {
        return when (event) {
            is CreateAccountEvent.Done -> {
                done(previousState = previousState)
            }

            is CreateAccountEvent.Error -> {
                error(previousState = previousState)
            }

            is CreateAccountEvent.TogglePasswordVisibility -> {
                togglePasswordVisibility(previousState = previousState)
            }

            is CreateAccountEvent.ToggleConfirmPasswordVisibility -> {
                toggleConfirmPasswordVisibility(previousState = previousState)
            }

            is CreateAccountEvent.DismissKeyboard -> {
                previousState to CreateAccountEffect.DismissKeyboard
            }

            is CreateAccountEvent.NavigateTo -> {
                navigateTo(previousState = previousState, destination = event.destination)
            }
        }
    }

    private fun done(
        previousState: CreateAccountState
    ): Pair<CreateAccountState, CreateAccountEffect?> {
        return if (previousState.createAccountFields.isValid()) {
            setValidCreateAccountState(previousState = previousState)
        } else {
            setInvalidCreateAccountState(previousState = previousState)
        }
    }

    private fun setValidCreateAccountState(
        previousState: CreateAccountState
    ): Pair<CreateAccountState, CreateAccountEffect?> {
        return previousState.copy(loading = true, error = false) to null
    }

    private fun setInvalidCreateAccountState(
        previousState: CreateAccountState
    ): Pair<CreateAccountState, CreateAccountEffect?> {
        return previousState.copy(loading = false, error = true) to CreateAccountEffect.ShowError
    }

    private fun error(
        previousState: CreateAccountState
    ): Pair<CreateAccountState, CreateAccountEffect?> {
        return previousState.copy(loading = false, error = true) to CreateAccountEffect.ShowError
    }

    private fun togglePasswordVisibility(
        previousState: CreateAccountState
    ): Pair<CreateAccountState, CreateAccountEffect?> {
        return previousState.copy(passwordVisible = !previousState.passwordVisible) to null
    }

    private fun toggleConfirmPasswordVisibility(
        previousState: CreateAccountState
    ): Pair<CreateAccountState, CreateAccountEffect?> {
        return previousState.copy(
            confirmPasswordVisible = !previousState.confirmPasswordVisible
        ) to null
    }

    private fun navigateTo(
        previousState: CreateAccountState,
        destination: CreateAccountDestination
    ): Pair<CreateAccountState, CreateAccountEffect?> {
        return previousState.copy(
            loading = false,
            error = false
        ) to CreateAccountEffect.NavigateTo(destination = destination)
    }
}