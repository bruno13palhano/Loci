package com.bruno13palhano.loci.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bruno13palhano.login.navigation.LoginRoutes
import com.bruno13palhano.login.navigation.loginScreen

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = LoginRoutes.MainLogin) {
        loginScreen(modifier = modifier, navController = navController)
    }
}