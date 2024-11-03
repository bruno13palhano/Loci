package com.bruno13palhano.loci.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bruno13palhano.account.navigation.CreateAccountRoutes
import com.bruno13palhano.account.navigation.createAccountScreen
import com.bruno13palhano.account.ui.presenter.CreateAccountDestination
import com.bruno13palhano.forgotpassword.navigation.ForgotPasswordRoutes
import com.bruno13palhano.forgotpassword.navigation.forgotPasswordScreen
import com.bruno13palhano.forgotpassword.ui.presenter.ForgotPasswordDestination
import com.bruno13palhano.home.navigation.HomeRoutes
import com.bruno13palhano.home.navigation.homeScreen
import com.bruno13palhano.home.ui.presenter.HomeDestination
import com.bruno13palhano.login.navigation.LoginRoutes
import com.bruno13palhano.login.navigation.loginScreen
import com.bruno13palhano.login.ui.login.presenter.LoginDestination
import com.bruno13palhano.messages.navigation.messagesScreen
import com.bruno13palhano.profile.navigation.profileScreen
import com.bruno13palhano.workspace.navigation.workspaceScreen

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    showBottomMenu: (show: Boolean) -> Unit
) {
    NavHost(navController = navController, startDestination = HomeRoutes.Home) {
        loginScreen(
            modifier = modifier,
            navigateTo = { navController.loginNavigateTo(destination = it) },
            showBottomMenu = showBottomMenu
        )

        createAccountScreen(
            modifier = modifier,
            navigateTo = { navController.createAccountNavigateTo(destination = it) },
            showBottomMenu = showBottomMenu
        )

        forgotPasswordScreen(
            modifier = modifier,
            navigateTo = { navController.forgotPasswordNavigateTo(destination = it) },
            showBottomMenu = showBottomMenu
        )

        homeScreen(
            modifier = modifier,
            showBottomMenu = showBottomMenu,
            navigateTo = { navController.homeNavigateTo(destination = it) }
        )

        workspaceScreen(
            modifier = modifier,
            showBottomMenu = showBottomMenu
        )

        messagesScreen(
            modifier = modifier,
            showBottomMenu = showBottomMenu
        )

        profileScreen(
            modifier = modifier,
            showBottomMenu = showBottomMenu
        )
    }
}

private fun NavController.loginNavigateTo(destination: LoginDestination) {
    when (destination) {
        is LoginDestination.Home -> {
            navigate(route = HomeRoutes.Home) {
                popUpTo<LoginRoutes.Login> {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }

        is LoginDestination.NewAccount -> navigate(route = CreateAccountRoutes.CreateAccount)

        is LoginDestination.ForgotPassword -> navigate(route = ForgotPasswordRoutes.ForgotPassword)
    }
}

private fun NavController.createAccountNavigateTo(destination: CreateAccountDestination) {
    when (destination) {
        is CreateAccountDestination.Home -> {}

        is CreateAccountDestination.Back -> navigateUp()
    }
}

private fun NavController.forgotPasswordNavigateTo(destination: ForgotPasswordDestination) {
    when (destination) {
        is ForgotPasswordDestination.Home -> {}

        is ForgotPasswordDestination.Back -> navigateUp()
    }
}

private fun NavController.homeNavigateTo(destination: HomeDestination) {
    when (destination) {
        is HomeDestination.Login -> {
            navigate(route = LoginRoutes.Login) {
                popUpTo<HomeRoutes.Home> {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}