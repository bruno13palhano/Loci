package com.bruno13palhano.profile.ui.presenter

import androidx.compose.runtime.Immutable
import com.bruno13palhano.ui.shared.ViewAction
import com.bruno13palhano.ui.shared.ViewEffect
import com.bruno13palhano.ui.shared.ViewEvent
import com.bruno13palhano.ui.shared.ViewState

@Immutable
internal data class HomeState(
    val loading: Boolean
) : ViewState {
    companion object {
        val Initial = HomeState(
            loading = false
        )
    }
}

@Immutable
internal sealed interface HomeEvent : ViewEvent {
    data object Refresh : HomeEvent
}

@Immutable
internal sealed interface HomeEffect : ViewEffect {
    data object ShowErrorInfo : HomeEffect
}

@Immutable
internal sealed interface HomeAction : ViewAction {
    data object OnRefresh : HomeAction
}