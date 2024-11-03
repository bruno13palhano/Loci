package com.bruno13palhano.login.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bruno13palhano.login.ui.login.presenter.LoginDestination
import com.bruno13palhano.login.ui.login.presenter.LoginRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.loginScreen(
    modifier: Modifier = Modifier,
    navigateTo: (destination: LoginDestination) -> Unit,
    showBottomMenu: (show: Boolean) -> Unit
) {
    composable<LoginRoutes.Login> {
        showBottomMenu(false)
        LoginRoute(
            modifier = modifier,
            navigateTo = navigateTo
        )
    }
}

sealed interface LoginRoutes {
    @Serializable
    data object Login : LoginRoutes
}