package com.bruno13palhano.workspace.ui.presenter

import com.bruno13palhano.ui.shared.Reducer

internal class WorkspaceReducer : Reducer<WorkspaceState, WorkspaceEvent, WorkspaceEffect> {
    override fun reduce(
        previousState: WorkspaceState,
        event: WorkspaceEvent
    ): Pair<WorkspaceState, WorkspaceEffect?> {
        return when (event) {
            is WorkspaceEvent.Refresh -> {
                previousState.copy(loading = true) to null
            }
        }
    }
}