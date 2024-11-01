package com.bruno13palhano.home.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bruno13palhano.home.ui.presenter.HomeRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.homeScreen(
    modifier: Modifier = Modifier,
    showBottomMenu: (show: Boolean) -> Unit,
    navigateToLogin: () -> Unit
) {
    composable<HomeRoutes.Home> {
        showBottomMenu(true)
        HomeRoute(modifier = modifier, navigateToLogin = navigateToLogin)
    }
}

sealed interface HomeRoutes {
    @Serializable
    data object Home : HomeRoutes
}