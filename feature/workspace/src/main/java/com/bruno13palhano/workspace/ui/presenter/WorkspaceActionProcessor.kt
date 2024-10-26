package com.bruno13palhano.workspace.ui.presenter

import com.bruno13palhano.ui.shared.ActionProcessor

internal class WorkspaceActionProcessor : ActionProcessor<WorkspaceAction, WorkspaceEvent> {
    override fun process(action: WorkspaceAction): WorkspaceEvent {
        return when (action) {
            is WorkspaceAction.OnRefresh -> WorkspaceEvent.Refresh
        }
    }
}