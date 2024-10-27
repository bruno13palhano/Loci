package com.bruno13palhano.messages.ui.presenter

import androidx.compose.runtime.Immutable
import com.bruno13palhano.ui.shared.ViewAction
import com.bruno13palhano.ui.shared.ViewEffect
import com.bruno13palhano.ui.shared.ViewEvent
import com.bruno13palhano.ui.shared.ViewState

@Immutable
internal data class MessagesState(
    val loading: Boolean
) : ViewState {
    companion object {
        val Initial = MessagesState(
            loading = false
        )
    }
}

@Immutable
internal sealed interface MessagesEvent : ViewEvent {
    data object Refresh : MessagesEvent
}

@Immutable
internal sealed interface MessagesEffect : ViewEffect {
    data object ShowErrorInfo : MessagesEffect
}

@Immutable
internal sealed interface MessagesAction : ViewAction {
    data object OnRefresh : MessagesAction
}