package com.bruno13palhano.login.ui.login.presenter

import com.bruno13palhano.ui.shared.Reducer

internal class LoginReducer: Reducer<LoginState, LoginEvent, LoginEffect> {
    override fun reduce(
        previousState: LoginState,
        event: LoginEvent
    ): Pair<LoginState, LoginEffect?> {
        return when (event) {
            is LoginEvent.Error -> {
                previousState.copy(loading = false, error = true) to LoginEffect.ShowError
            }

            is LoginEvent.Login -> login(previousState = previousState)

            is LoginEvent.TogglePasswordVisibility -> {
                previousState.copy(passwordVisible = !previousState.passwordVisible) to null
            }

            is LoginEvent.DismissKeyboard -> previousState to LoginEffect.DismissKeyboard

            is LoginEvent.NavigateTo -> {
                navigateTo(previousState = previousState, destination = event.destination)
            }
        }
    }

    private fun login(previousState: LoginState): Pair<LoginState, LoginEffect?> {
        return if (previousState.loginFields.isValid()) {
            setValidLoginState(previousState = previousState)
        } else {
            setInvalidLoginState(previousState = previousState)
        }
    }

    private fun setValidLoginState(previousState: LoginState): Pair<LoginState, LoginEffect?> {
        return previousState.copy(loading = true, error = false) to null
    }

    private fun setInvalidLoginState(previousState: LoginState): Pair<LoginState, LoginEffect> {
        return previousState.copy(loading = false, error = true) to LoginEffect.ShowError
    }

    private fun navigateTo(
        previousState: LoginState,
        destination: LoginDestination
    ): Pair<LoginState, LoginEffect?> {
        return previousState to getDestinationEffect(destination = destination)
    }

    private fun getDestinationEffect(destination: LoginDestination): LoginEffect {
        return when (destination) {
            is LoginDestination.Home -> LoginEffect.NavigateTo(destination)

            is LoginDestination.NewAccount -> LoginEffect.NavigateTo(destination)

            is LoginDestination.ForgotPassword -> LoginEffect.NavigateTo(destination)
        }
    }
}