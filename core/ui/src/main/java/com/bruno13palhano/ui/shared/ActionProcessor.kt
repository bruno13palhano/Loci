package com.bruno13palhano.ui.shared

interface ActionProcessor<Action: ViewAction, Event: ViewEvent> {
    fun process(action: Action): Event
}