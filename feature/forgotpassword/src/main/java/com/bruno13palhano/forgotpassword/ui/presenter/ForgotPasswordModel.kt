package com.bruno13palhano.forgotpassword.ui.presenter

import androidx.compose.runtime.Immutable
import com.bruno13palhano.ui.shared.ViewAction
import com.bruno13palhano.ui.shared.ViewEffect
import com.bruno13palhano.ui.shared.ViewEvent
import com.bruno13palhano.ui.shared.ViewState

@Immutable
internal data class ForgotPasswordState(
    val loading: Boolean,
    val emailVerified: Boolean,
    val forgotPasswordFields: ForgotPasswordFields,
    val error: Boolean
) : ViewState {
    companion object {
        val Initial = ForgotPasswordState(
            loading = false,
            emailVerified = false,
            forgotPasswordFields = ForgotPasswordFields(),
            error = false
        )
    }
}

@Immutable
internal sealed interface ForgotPasswordEvent : ViewEvent {
    data object VerifyEmail : ForgotPasswordEvent
    data object UpdatePassword : ForgotPasswordEvent
    data object Error : ForgotPasswordEvent
    data object NavigateBack : ForgotPasswordEvent
    data object NavigateToHome : ForgotPasswordEvent
}

@Immutable
internal sealed interface ForgotPasswordEffect : ViewEffect {
    data object ShowError : ForgotPasswordEffect
    data object NavigateBack : ForgotPasswordEffect
    data object NavigateToHome : ForgotPasswordEffect
}

@Immutable
internal sealed interface ForgotPasswordAction : ViewAction {
    data object OnVerifyEmail : ForgotPasswordAction
    data object OnUpdatePassword : ForgotPasswordAction
    data object OnNavigateBack : ForgotPasswordAction
}