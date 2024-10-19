package com.bruno13palhano.account.ui.presenter

import com.bruno13palhano.ui.shared.ActionProcessor

internal class CreateAccountActionProcessor : ActionProcessor<CreateAccountAction, CreateAccountEvent> {
    override fun process(action: CreateAccountAction): CreateAccountEvent {
        return when (action) {
            CreateAccountAction.OnDone -> CreateAccountEvent.Done

            CreateAccountAction.OnTogglePasswordVisibility -> {
                CreateAccountEvent.TogglePasswordVisibility
            }

            CreateAccountAction.OnToggleConfirmPasswordVisibility -> {
                CreateAccountEvent.ToggleConfirmPasswordVisibility
            }
        }
    }
}