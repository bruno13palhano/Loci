package com.bruno13palhano.login.ui.login.presenter

import androidx.compose.runtime.Immutable
import com.bruno13palhano.ui.shared.ViewAction
import com.bruno13palhano.ui.shared.ViewEffect
import com.bruno13palhano.ui.shared.ViewEvent
import com.bruno13palhano.ui.shared.ViewState

@Immutable
internal data class LoginState(
    val loading: Boolean,
    val loginFields: LoginFields,
    val error: Boolean,
    val passwordVisible: Boolean
) : ViewState {
    companion object {
        val Initial = LoginState(
            loading = false,
            loginFields = LoginFields(),
            error = false,
            passwordVisible = false
        )
    }
}

@Immutable
internal sealed interface LoginEvent : ViewEvent {
    data object Login : LoginEvent
    data object Error : LoginEvent
    data object TogglePasswordVisibility : LoginEvent
    data object DismissKeyboard : LoginEvent
    data object NavigateToHome : LoginEvent
    data object NavigateToNewAccount : LoginEvent
    data object NavigateToForgotPassword : LoginEvent
}

@Immutable
internal sealed interface LoginEffect : ViewEffect {
    data object ShowError : LoginEffect
    data object DismissKeyboard : LoginEffect
    data object NavigateToHome: LoginEffect
    data object NavigateToNewAccount : LoginEffect
    data object NavigateToForgotPassword : LoginEffect
}

@Immutable
internal sealed interface LoginAction : ViewAction {
    data object OnLogin : LoginAction
    data object OnTogglePasswordVisibility : LoginAction
    data object OnDismissKeyboard : LoginAction
    data object OnNavigateToNewAccount : LoginAction
    data object OnNavigateToForgotPassword : LoginAction
}