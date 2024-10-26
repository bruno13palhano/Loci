package com.bruno13palhano.workspace.ui.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.bruno13palhano.ui.shared.Reducer
import kotlinx.coroutines.flow.Flow

@Composable
internal fun workspacePresenter(
    reducer: Reducer<WorkspaceState, WorkspaceEvent, WorkspaceEffect>,
    events: Flow<WorkspaceEvent>,
    sendEvent: (event: WorkspaceEvent) -> Unit,
    sendEffect: (effect: WorkspaceEffect) -> Unit
): WorkspaceState {
    val state = remember { mutableStateOf(WorkspaceState.Initial) }

    HandleEvents(events = events, state = state, reducer = reducer, sendEffect = sendEffect)

    return state.value
}

@Composable
private fun HandleEvents(
    events: Flow<WorkspaceEvent>,
    state: MutableState<WorkspaceState>,
    reducer: Reducer<WorkspaceState, WorkspaceEvent, WorkspaceEffect>,
    sendEffect: (effect: WorkspaceEffect) -> Unit
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