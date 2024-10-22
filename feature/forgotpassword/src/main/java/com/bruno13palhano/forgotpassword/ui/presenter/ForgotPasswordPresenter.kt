package com.bruno13palhano.forgotpassword.ui.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.bruno13palhano.ui.shared.Reducer
import kotlinx.coroutines.flow.Flow

@Composable
internal fun forgotPasswordPresenter(
    reducer: Reducer<ForgotPasswordState, ForgotPasswordEvent, ForgotPasswordEffect>,
    events: Flow<ForgotPasswordEvent>,
    sendEvent: (event: ForgotPasswordEvent) -> Unit,
    sendEffect: (effect: ForgotPasswordEffect) -> Unit
): ForgotPasswordState {
    val state = remember { mutableStateOf(ForgotPasswordState.Initial) }

    LaunchedEffect(Unit) {
        events.collect { event ->
            reducer.reduce(state.value, event).let {
                state.value = it.first
                it.second?.let(sendEffect)
            }
        }
    }

    return state.value
}