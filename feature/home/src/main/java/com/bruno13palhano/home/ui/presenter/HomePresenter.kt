package com.bruno13palhano.home.ui.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.bruno13palhano.data.user.UserRepository
import com.bruno13palhano.ui.shared.Reducer
import kotlinx.coroutines.flow.Flow

@Composable
internal fun homePresenter(
    repository: UserRepository,
    reducer: Reducer<HomeState, HomeEvent, HomeEffect>,
    events: Flow<HomeEvent>,
    sendEvent: (event: HomeEvent) -> Unit,
    sendEffect: (effect: HomeEffect) -> Unit
) : HomeState {
    val state = remember { mutableStateOf(HomeState.Initial) }

    HandleEvents(events = events, state = state, reducer = reducer, sendEffect = sendEffect)

    Authentication(repository = repository, sendEvent = sendEvent)

    return state.value
}

@Composable
private fun HandleEvents(
    events: Flow<HomeEvent>,
    state: MutableState<HomeState>,
    reducer: Reducer<HomeState, HomeEvent, HomeEffect>,
    sendEffect: (effect: HomeEffect) -> Unit
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

@Composable
internal fun Authentication(
    repository: UserRepository,
    sendEvent: (event: HomeEvent) -> Unit
) {
    LaunchedEffect(Unit) {

        if (!repository.authenticated()) {
            sendEvent(HomeEvent.NavigateTo(destination = HomeDestination.Login))
        }
    }
}