package com.bruno13palhano.login.ui.presenter

import com.bruno13palhano.ui.shared.ActionProcessor

internal class LoginActionProcessor : ActionProcessor<LoginAction, LoginEvent> {
    override fun process(action: LoginAction): LoginEvent {
        return when (action) {
            is LoginAction.OnLogin -> LoginEvent.Login

            is LoginAction.OnNavigateToNewAccount -> LoginEvent.NavigateToNewAccount

            is LoginAction.OnTogglePasswordVisibility -> LoginEvent.TogglePasswordVisibility
        }
    }
}