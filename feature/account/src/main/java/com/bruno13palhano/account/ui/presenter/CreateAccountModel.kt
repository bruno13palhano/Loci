package com.bruno13palhano.account.ui.presenter

import androidx.compose.runtime.Immutable
import com.bruno13palhano.ui.shared.ViewAction
import com.bruno13palhano.ui.shared.ViewEffect
import com.bruno13palhano.ui.shared.ViewEvent
import com.bruno13palhano.ui.shared.ViewState

@Immutable
internal data class CreateAccountState(
    val loading: Boolean,
    val createAccountFields: CreateAccountFields,
    val error: Boolean,
    val passwordVisible: Boolean,
    val confirmPasswordVisible: Boolean
) : ViewState {
    companion object {
        val Initial = CreateAccountState(
            loading = false,
            createAccountFields = CreateAccountFields(),
            error = false,
            passwordVisible = false,
            confirmPasswordVisible = false
        )
    }
}

@Immutable
internal sealed interface CreateAccountEvent : ViewEvent {
    data object Done : CreateAccountEvent
    data object Error : CreateAccountEvent
    data object TogglePasswordVisibility : CreateAccountEvent
    data object ToggleConfirmPasswordVisibility : CreateAccountEvent
    data object NavigateToHome : CreateAccountEvent
    data object NavigateBack : CreateAccountEvent
}

@Immutable
internal sealed interface CreateAccountEffect : ViewEffect {
    data object ShowError : CreateAccountEffect
    data object NavigateToHome : CreateAccountEffect
    data object NavigateBack : CreateAccountEffect
}

@Immutable
internal sealed interface CreateAccountAction : ViewAction {
    data object OnDone : CreateAccountAction
    data object OnTogglePasswordVisibility : CreateAccountAction
    data object OnToggleConfirmPasswordVisibility : CreateAccountAction
    data object OnNavigateBack : CreateAccountAction
}