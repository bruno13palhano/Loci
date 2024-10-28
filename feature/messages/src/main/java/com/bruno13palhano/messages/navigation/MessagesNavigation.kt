package com.bruno13palhano.messages.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bruno13palhano.messages.ui.presenter.MessagesRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.messagesScreen(
    modifier: Modifier = Modifier,
    showBottomMenu: (show: Boolean) -> Unit
) {
    composable<MessagesRoutes.Messages> {
        showBottomMenu(true)
        MessagesRoute(modifier = modifier)
    }
}

sealed interface MessagesRoutes {
    @Serializable
    data object Messages : MessagesRoutes
}