package com.bruno13palhano.home.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bruno13palhano.home.ui.presenter.HomeDestination
import com.bruno13palhano.home.ui.presenter.HomeRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.homeScreen(
    modifier: Modifier = Modifier,
    showBottomMenu: (show: Boolean) -> Unit,
    navigateTo: (destination: HomeDestination) -> Unit
) {
    composable<HomeRoutes.Home> {
        showBottomMenu(true)
        HomeRoute(modifier = modifier, navigateTo = navigateTo)
    }
}

sealed interface HomeRoutes {
    @Serializable
    data object Home : HomeRoutes
}