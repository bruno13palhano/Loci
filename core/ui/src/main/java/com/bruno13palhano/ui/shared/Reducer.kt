package com.bruno13palhano.ui.shared

/**
 * Interface for a reducer.
 *
 * @param State the state of the view.
 *
 * @param Event the event to change the state and trigger a side effect if needed.
 *
 * @param Effect the side effect that this event will trigger.
 */
interface Reducer<State: ViewState, Event: ViewEvent, Effect: ViewEffect> {
    /**
     * Reduce the state of the view based on the event.
     *
     * @param previousState the previous state of the view.
     *
     * @param event the event to change the state and trigger a side effect if necessary.
     *
     * @return a [Pair] of the new [State] and the side [Effect] that will be
     * triggered if necessary.
     */
    fun reduce(previousState: State, event: Event): Pair<State, Effect?>
}