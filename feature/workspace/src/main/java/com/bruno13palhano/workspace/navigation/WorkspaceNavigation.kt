package com.bruno13palhano.workspace.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bruno13palhano.workspace.ui.presenter.WorkspaceRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.workspaceScreen(
    modifier: Modifier = Modifier,
    showBottomMenu: (show: Boolean) -> Unit
) {
    composable<WorkspaceRoutes.Workspace> {
        showBottomMenu(true)
        WorkspaceRoute(modifier = modifier)
    }
}

sealed interface WorkspaceRoutes {
    @Serializable
    data object Workspace : WorkspaceRoutes
}