package com.bruno13palhano.loci.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bruno13palhano.account.navigation.CreateAccountRoutes
import com.bruno13palhano.account.navigation.createAccountScreen
import com.bruno13palhano.forgotpassword.navigation.ForgotPasswordRoutes
import com.bruno13palhano.forgotpassword.navigation.forgotPasswordScreen
import com.bruno13palhano.login.navigation.LoginRoutes
import com.bruno13palhano.login.navigation.loginScreen

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    showBottomMenu: (show: Boolean) -> Unit
) {
    NavHost(navController = navController, startDestination = LoginRoutes.Login) {
        loginScreen(
            modifier = modifier,
            navigateToHome = {},
            navigateToNewAccount = {
                navController.navigate(route = CreateAccountRoutes.CreateAccount)
            },
            navigateToForgotPassword = {
                navController.navigate(route = ForgotPasswordRoutes.ForgotPassword)
            },
            showBottomMenu = showBottomMenu
        )

        createAccountScreen(
            modifier = modifier,
            navigateToHome = {},
            navigateBack = { navController.navigateUp() },
            showBottomMenu = showBottomMenu
        )

        forgotPasswordScreen(
            modifier = modifier,
            navigateToHome = {},
            navigateBack = { navController.navigateUp() },
            showBottomMenu = showBottomMenu
        )
    }
}