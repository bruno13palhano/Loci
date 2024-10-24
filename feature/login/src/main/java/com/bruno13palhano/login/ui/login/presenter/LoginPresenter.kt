package com.bruno13palhano.login.ui.login.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.bruno13palhano.ui.shared.Reducer
import kotlinx.coroutines.flow.Flow

@Composable
internal fun loginPresenter(
    reducer: Reducer<LoginState, LoginEvent, LoginEffect>,
    events: Flow<LoginEvent>,
    sendEvent: (event: LoginEvent) -> Unit,
    sendEffect: (effect: LoginEffect) -> Unit
): LoginState {
    val state = remember { mutableStateOf(LoginState.Initial) }

    HandleEvents(events = events, state = state, reducer = reducer, sendEffect = sendEffect)

    return state.value
}

@Composable
private fun HandleEvents(
    events: Flow<LoginEvent>,
    state: MutableState<LoginState>,
    reducer: Reducer<LoginState, LoginEvent, LoginEffect>,
    sendEffect: (effect: LoginEffect) -> Unit
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