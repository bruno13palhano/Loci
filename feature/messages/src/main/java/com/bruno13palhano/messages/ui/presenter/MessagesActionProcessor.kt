package com.bruno13palhano.messages.ui.presenter

import com.bruno13palhano.ui.shared.ActionProcessor

internal class MessagesActionProcessor : ActionProcessor<MessagesAction, MessagesEvent> {
    override fun process(action: MessagesAction): MessagesEvent {
        return when (action) {
            is MessagesAction.OnRefresh -> MessagesEvent.Refresh
        }
    }
}