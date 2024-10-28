package com.bruno13palhano.messages.ui.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.bruno13palhano.ui.shared.Reducer
import kotlinx.coroutines.flow.Flow

@Composable
internal fun messagesPresenter(
    reducer: Reducer<MessagesState, MessagesEvent, MessagesEffect>,
    events: Flow<MessagesEvent>,
    sendEvent: (event: MessagesEvent) -> Unit,
    sendEffect: (effect: MessagesEffect) -> Unit
): MessagesState {
    val state = remember { mutableStateOf(MessagesState.Initial) }

    HandleEvents(events = events, state = state, reducer = reducer, sendEffect = sendEffect)

    return state.value
}

@Composable
private fun HandleEvents(
    events: Flow<MessagesEvent>,
    state: MutableState<MessagesState>,
    reducer: Reducer<MessagesState, MessagesEvent, MessagesEffect>,
    sendEffect: (effect: MessagesEffect) -> Unit
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