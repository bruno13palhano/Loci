package com.bruno13palhano.loci.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bruno13palhano.account.navigation.createAccountScreen
import com.bruno13palhano.forgotpassword.navigation.forgotPasswordScreen
import com.bruno13palhano.home.navigation.HomeRoutes
import com.bruno13palhano.home.navigation.homeScreen
import com.bruno13palhano.login.navigation.loginScreen
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