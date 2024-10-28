package com.bruno13palhano.messages.ui.viewmodel

import androidx.compose.runtime.Composable
import com.bruno13palhano.messages.ui.presenter.MessagesAction
import com.bruno13palhano.messages.ui.presenter.MessagesActionProcessor
import com.bruno13palhano.messages.ui.presenter.MessagesEffect
import com.bruno13palhano.messages.ui.presenter.MessagesEvent
import com.bruno13palhano.messages.ui.presenter.MessagesReducer
import com.bruno13palhano.messages.ui.presenter.MessagesState
import com.bruno13palhano.messages.ui.presenter.messagesPresenter
import com.bruno13palhano.ui.shared.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
internal class MessagesViewModel @Inject constructor(

) : BaseViewModel<MessagesState, MessagesEvent, MessagesEffect, MessagesAction>(
    actionProcessor = MessagesActionProcessor(),
    reducer = MessagesReducer()
) {
    @Composable
    override fun states(events: Flow<MessagesEvent>): MessagesState {
        return messagesPresenter(
            reducer = reducer,
            events = events,
            sendEvent = ::sendEvent,
            sendEffect = ::sendEffect
        )
    }
}