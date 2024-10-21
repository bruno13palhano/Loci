package com.bruno13palhano.ui.shared

/**
 * Interface for the action processor.
 *
 * @param Action The type of action to process.
 *
 * @param Event The type of event to return.
 */
interface ActionProcessor<Action: ViewAction, Event: ViewEvent> {
    /**
     * Processes the given action and returns an event.
     *
     * @param action The [Action] to process.
     *
     * @return The [Event] resulting from processing the action.
     */
    fun process(action: Action): Event
}