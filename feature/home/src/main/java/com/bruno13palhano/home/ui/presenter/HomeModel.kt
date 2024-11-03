package com.bruno13palhano.home.ui.presenter

import androidx.compose.runtime.Immutable
import com.bruno13palhano.ui.shared.ViewAction
import com.bruno13palhano.ui.shared.ViewEffect
import com.bruno13palhano.ui.shared.ViewEvent
import com.bruno13palhano.ui.shared.ViewState

@Immutable
internal data class HomeState(
    val loading: Boolean,
    val authenticated: Boolean
) : ViewState {
    companion object {
        val Initial = HomeState(
            loading = false,
            authenticated = false
        )
    }
}

@Immutable
internal sealed interface HomeEvent : ViewEvent {
    data object Refresh : HomeEvent
    data class NavigateTo(val destination: HomeDestination) : HomeEvent
}

@Immutable
internal sealed interface HomeEffect : ViewEffect {
    data object ShowErrorInfo : HomeEffect
    data class NavigateTo(val destination: HomeDestination) : HomeEffect
}

@Immutable
internal sealed interface HomeAction : ViewAction {
    data object OnRefresh : HomeAction
}

@Immutable
sealed interface HomeDestination {
    data object Login : HomeDestination
}