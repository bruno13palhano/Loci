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
    val passwordVisible: Boolean,
    val confirmPasswordVisible: Boolean,
    val invalidFields: Boolean,
    val internalError: Boolean,
    val showErrorInfo: Boolean
) : ViewState {
    companion object {
        val Initial = ForgotPasswordState(
            loading = false,
            emailVerified = false,
            forgotPasswordFields = ForgotPasswordFields(),
            passwordVisible = false,
            confirmPasswordVisible = false,
            invalidFields = false,
            internalError = false,
            showErrorInfo = false
        )
    }
}

@Immutable
internal sealed interface ForgotPasswordEvent : ViewEvent {
    data object VerifyEmail : ForgotPasswordEvent
    data object UpdatePassword : ForgotPasswordEvent
    data object CancelUpdatePassword : ForgotPasswordEvent
    data object TogglePasswordVisibility : ForgotPasswordEvent
    data object ToggleConfirmPasswordVisibility : ForgotPasswordEvent
    data class InternalError(val errorType: ErrorType) : ForgotPasswordEvent
    data object NavigateBack : ForgotPasswordEvent
    data object NavigateToHome : ForgotPasswordEvent
}

@Immutable
internal sealed interface ForgotPasswordEffect : ViewEffect {
    data class ShowErrorInfo(val errorType: ErrorType) : ForgotPasswordEffect
    data object NavigateBack : ForgotPasswordEffect
    data object NavigateToHome : ForgotPasswordEffect
}

@Immutable
internal sealed interface ForgotPasswordAction : ViewAction {
    data object OnVerifyEmail : ForgotPasswordAction
    data object OnUpdatePassword : ForgotPasswordAction
    data object OnCancelUpdatePassword : ForgotPasswordAction
    data object OnTogglePasswordVisibility : ForgotPasswordAction
    data object OnToggleConfirmPasswordVisibility : ForgotPasswordAction
    data object OnNavigateBack : ForgotPasswordAction
}

@Immutable
internal sealed interface ErrorType {
    data object NetworkError : ErrorType
    data object GenericError : ErrorType
    data object FillMissingField : ErrorType
    data object InvalidEmail : ErrorType
    data object PasswordDoesNotMatch : ErrorType
}