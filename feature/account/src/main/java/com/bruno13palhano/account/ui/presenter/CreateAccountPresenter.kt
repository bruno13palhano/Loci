package com.bruno13palhano.account.ui.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.bruno13palhano.ui.shared.Reducer
import kotlinx.coroutines.flow.Flow

@Composable
internal fun createAccountPresenter(
    reducer: Reducer<CreateAccountState, CreateAccountEvent, CreateAccountEffect>,
    events: Flow<CreateAccountEvent>,
    sendEvent: (event: CreateAccountEvent) -> Unit,
    sendEffect: (effect: CreateAccountEffect) -> Unit
): CreateAccountState {
    val state = remember { mutableStateOf(CreateAccountState.Initial) }

    LaunchedEffect(Unit) {
        events.collect { event ->
            reducer.reduce(previousState = state.value, event = event).let {
                state.value = it.first
                it.second?.let(sendEffect)
            }
        }
    }

    return state.value
}