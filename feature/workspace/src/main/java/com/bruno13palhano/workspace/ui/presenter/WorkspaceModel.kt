package com.bruno13palhano.workspace.ui.presenter

import androidx.compose.runtime.Immutable
import com.bruno13palhano.ui.shared.ViewAction
import com.bruno13palhano.ui.shared.ViewEffect
import com.bruno13palhano.ui.shared.ViewEvent
import com.bruno13palhano.ui.shared.ViewState

@Immutable
internal data class WorkspaceState(
    val loading: Boolean
) : ViewState {
    companion object {
        val Initial = WorkspaceState(loading = false)
    }
}

@Immutable
internal sealed interface WorkspaceEvent : ViewEvent{
    data object Refresh : WorkspaceEvent
}

@Immutable
internal sealed interface WorkspaceEffect : ViewEffect {
    data object ShowErrorInfo : WorkspaceEffect
}

@Immutable
internal sealed interface WorkspaceAction : ViewAction {
    data object OnRefresh : WorkspaceAction
}