package com.bruno13palhano.account.ui.presenter

import com.bruno13palhano.ui.shared.ActionProcessor

internal class CreateAccountActionProcessor : ActionProcessor<CreateAccountAction, CreateAccountEvent> {
    override fun process(action: CreateAccountAction): CreateAccountEvent {
        return when (action) {
            is CreateAccountAction.OnDone -> CreateAccountEvent.Done

            is CreateAccountAction.OnTogglePasswordVisibility -> {
                CreateAccountEvent.TogglePasswordVisibility
            }

            is CreateAccountAction.OnToggleConfirmPasswordVisibility -> {
                CreateAccountEvent.ToggleConfirmPasswordVisibility
            }

            is CreateAccountAction.OnNavigateTo -> {
                CreateAccountEvent.NavigateTo(destination = action.destination)
            }
        }
    }
}