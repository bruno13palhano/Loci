package com.bruno13palhano.ui.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AndroidUiDispatcher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.molecule.RecompositionMode.ContextClock
import app.cash.molecule.launchMolecule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * Base ViewModel to coordinate the states, events and side effect operations.
 *
 * @property actionProcessor The [ActionProcessor] to process the actions.
 *
 * @property reducer The [Reducer] to reduce the states.
 *
 * @property state The [StateFlow] containing the current state to be collected.
 *
 * @property effects The [Flow] with the side effects to be collected.
 */
abstract class BaseViewModel<State: ViewState, Event: ViewEvent, Effect: ViewEffect, Action: ViewAction>(
    protected val actionProcessor: ActionProcessor<Action, Event>,
    protected val reducer: Reducer<State, Event, Effect>
) : ViewModel() {
    private val scope = CoroutineScope(viewModelScope.coroutineContext + AndroidUiDispatcher.Main)

    private val events: MutableSharedFlow<Event> = MutableSharedFlow(extraBufferCapacity = 20)

    private val _effects = Channel<Effect>(capacity = Channel.CONFLATED)
    val effects = _effects.receiveAsFlow()

    val state: StateFlow<State> by lazy(LazyThreadSafetyMode.NONE) {
        scope.launchMolecule(mode = ContextClock) {
            states(events = events)
        }
    }

    /**
     * Update the state based on the event in a reactive way.
     *
     * @param events The [Flow] containing the [Event] to be processed.
     *
     * @return The new [State].
     */
    @Composable
    protected abstract fun states(events: Flow<Event>): State

    /**
     * Called by the View to process an action.
     */
    fun onAction(action: Action) {
        sendEvent(event = actionProcessor.process(action))
    }

    /**
     * Called by the ViewModel to send an event.
     */
    protected fun sendEvent(event: Event) {
        events.tryEmit(event)
    }

    /**
     * Called by the ViewModel to send an effect.
     */
    protected fun sendEffect(effect: Effect) {
        _effects.trySend(effect)
    }
}