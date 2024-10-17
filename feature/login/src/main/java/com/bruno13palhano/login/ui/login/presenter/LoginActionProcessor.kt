package com.bruno13palhano.login.ui.login.presenter

import com.bruno13palhano.ui.shared.ActionProcessor

internal class LoginActionProcessor : ActionProcessor<LoginAction, LoginEvent> {
    override fun process(action: LoginAction): LoginEvent {
        return when (action) {
            is LoginAction.OnLogin -> LoginEvent.Login

            is LoginAction.OnTogglePasswordVisibility -> LoginEvent.TogglePasswordVisibility

            is LoginAction.OnNavigateToNewAccount -> LoginEvent.NavigateToNewAccount

            is LoginAction.OnNavigateToForgotPassword -> LoginEvent.NavigateToForgotPassword
        }
    }
}