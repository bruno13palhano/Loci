package com.bruno13palhano.profile.ui.presenter

import androidx.compose.runtime.Immutable
import com.bruno13palhano.ui.shared.ViewAction
import com.bruno13palhano.ui.shared.ViewEffect
import com.bruno13palhano.ui.shared.ViewEvent
import com.bruno13palhano.ui.shared.ViewState

@Immutable
internal data class ProfileState(
    val loading: Boolean
) : ViewState {
    companion object {
        val Initial = ProfileState(
            loading = false
        )
    }
}

@Immutable
internal sealed interface ProfileEvent : ViewEvent {
    data object Refresh : ProfileEvent
}

@Immutable
internal sealed interface ProfileEffect : ViewEffect {
    data object ShowErrorInfo : ProfileEffect
}

@Immutable
internal sealed interface ProfileAction : ViewAction {
    data object OnRefresh : ProfileAction
}