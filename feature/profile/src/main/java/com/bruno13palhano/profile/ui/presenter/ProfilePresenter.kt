package com.bruno13palhano.profile.ui.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.bruno13palhano.ui.shared.Reducer
import kotlinx.coroutines.flow.Flow

@Composable
internal fun profilePresenter(
    reducer: Reducer<ProfileState, ProfileEvent, ProfileEffect>,
    events: Flow<ProfileEvent>,
    sendEvent: (event: ProfileEvent) -> Unit,
    sendEffect: (effect: ProfileEffect) -> Unit
): ProfileState {
    val state = remember { mutableStateOf(ProfileState.Initial) }

    HandleEvents(events = events, state = state, reducer = reducer, sendEffect = sendEffect)

    return state.value
}

@Composable
private fun HandleEvents(
    events: Flow<ProfileEvent>,
    state: MutableState<ProfileState>,
    reducer: Reducer<ProfileState, ProfileEvent, ProfileEffect>,
    sendEffect: (effect: ProfileEffect) -> Unit
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