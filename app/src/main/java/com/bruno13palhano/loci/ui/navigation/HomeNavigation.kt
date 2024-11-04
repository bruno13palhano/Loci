package com.bruno13palhano.loci.ui.navigation

import androidx.navigation.NavController
import com.bruno13palhano.home.navigation.HomeRoutes
import com.bruno13palhano.home.ui.presenter.HomeDestination
import com.bruno13palhano.login.navigation.LoginRoutes

internal fun NavController.homeNavigateTo(destination: HomeDestination) {
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