package com.bruno13palhano.login.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bruno13palhano.login.ui.login.presenter.LoginRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.loginScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    showBottomMenu: (show: Boolean) -> Unit
) {
    navigation<LoginRoutes.MainLogin>(startDestination = LoginRoutes.Login) {
        composable<LoginRoutes.Login> {
            showBottomMenu(false)
            LoginRoute(
                modifier = modifier,
                navigateToHome = {},
                navigateToNewAccount = {}
            )
        }
    }
}

sealed interface LoginRoutes {
    @Serializable
    data object MainLogin : LoginRoutes

    @Serializable
    data object Login : LoginRoutes

    @Serializable
    data object CreateAccount : LoginRoutes
}