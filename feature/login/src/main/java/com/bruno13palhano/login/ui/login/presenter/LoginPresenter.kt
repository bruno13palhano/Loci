package com.bruno13palhano.login.ui.login.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.bruno13palhano.ui.shared.Reducer
import kotlinx.coroutines.flow.Flow

@Composable
internal fun loginPresenter(
    loginFields: LoginFields,
    reducer: Reducer<LoginState, LoginEvent, LoginEffect>,
    events: Flow<LoginEvent>,
    sendEffect: (effect: LoginEffect) -> Unit
): LoginState {
    val state = remember { mutableStateOf(LoginState.Initial) }

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

private suspend fun login() {

}