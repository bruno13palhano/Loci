package com.bruno13palhano.messages.ui.presenter

import com.bruno13palhano.ui.shared.Reducer

internal class MessagesReducer : Reducer<MessagesState, MessagesEvent, MessagesEffect> {
    override fun reduce(
        previousState: MessagesState,
        event: MessagesEvent
    ): Pair<MessagesState, MessagesEffect?> {
        return when (event) {
            is MessagesEvent.Refresh -> {
                previousState.copy(loading = true) to null
            }
        }
    }
}