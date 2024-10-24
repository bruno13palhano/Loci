package com.bruno13palhano.forgotpassword.ui.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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

    HandleEvents(events = events, state = state, reducer = reducer, sendEffect = sendEffect)

    return state.value
}

@Composable
private fun HandleEvents(
    events: Flow<ForgotPasswordEvent>,
    state: MutableState<ForgotPasswordState>,
    reducer: Reducer<ForgotPasswordState, ForgotPasswordEvent, ForgotPasswordEffect>,
    sendEffect: (effect: ForgotPasswordEffect) -> Unit
) {
    LaunchedEffect(Unit) {
        events.collect { event ->
            reducer.reduce(previousState = state.value, event = event).let {
                state.value = it.first
                it.second?.let(sendEffect)
            }
        }
    }
}