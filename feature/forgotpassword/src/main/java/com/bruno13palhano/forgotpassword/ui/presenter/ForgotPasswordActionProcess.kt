package com.bruno13palhano.forgotpassword.ui.presenter

import com.bruno13palhano.ui.shared.ActionProcessor

internal class ForgotPasswordActionProcess : ActionProcessor<ForgotPasswordAction, ForgotPasswordEvent> {
    override fun process(action: ForgotPasswordAction): ForgotPasswordEvent {
        return when (action) {
            is ForgotPasswordAction.OnVerifyEmail -> ForgotPasswordEvent.VerifyEmail

            is ForgotPasswordAction.OnUpdatePassword -> ForgotPasswordEvent.UpdatePassword

            is ForgotPasswordAction.OnTogglePasswordVisibility -> {
                ForgotPasswordEvent.TogglePasswordVisibility
            }

            is ForgotPasswordAction.OnToggleConfirmPasswordVisibility -> {
                ForgotPasswordEvent.ToggleConfirmPasswordVisibility
            }

            is ForgotPasswordAction.OnCancelUpdatePassword -> {
                ForgotPasswordEvent.CancelUpdatePassword
            }

            is ForgotPasswordAction.OnNavigateBack -> ForgotPasswordEvent.NavigateBack
        }
    }
}