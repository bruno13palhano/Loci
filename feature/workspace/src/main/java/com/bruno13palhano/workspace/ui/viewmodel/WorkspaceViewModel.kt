package com.bruno13palhano.workspace.ui.viewmodel

import androidx.compose.runtime.Composable
import com.bruno13palhano.ui.shared.BaseViewModel
import com.bruno13palhano.workspace.ui.presenter.WorkspaceAction
import com.bruno13palhano.workspace.ui.presenter.WorkspaceActionProcessor
import com.bruno13palhano.workspace.ui.presenter.WorkspaceEffect
import com.bruno13palhano.workspace.ui.presenter.WorkspaceEvent
import com.bruno13palhano.workspace.ui.presenter.WorkspaceReducer
import com.bruno13palhano.workspace.ui.presenter.WorkspaceState
import com.bruno13palhano.workspace.ui.presenter.workspacePresenter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
internal class WorkspaceViewModel @Inject constructor(

): BaseViewModel<WorkspaceState, WorkspaceEvent, WorkspaceEffect, WorkspaceAction>(
    actionProcessor = WorkspaceActionProcessor(),
    reducer = WorkspaceReducer()
) {
    @Composable
    override fun states(events: Flow<WorkspaceEvent>): WorkspaceState {
        return workspacePresenter(
            reducer = reducer,
            events = events,
            sendEvent = ::sendEvent,
            sendEffect = ::sendEffect
        )
    }
}