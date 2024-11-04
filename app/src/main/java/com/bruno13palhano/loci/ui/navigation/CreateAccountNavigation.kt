package com.bruno13palhano.loci.ui.navigation

import androidx.navigation.NavController
import com.bruno13palhano.account.navigation.CreateAccountRoutes
import com.bruno13palhano.account.ui.presenter.CreateAccountDestination
import com.bruno13palhano.home.navigation.HomeRoutes

internal fun NavController.createAccountNavigateTo(destination: CreateAccountDestination) {
    when (destination) {
        is CreateAccountDestination.Home -> {
            navigate(route = HomeRoutes.Home) {
                popUpTo<CreateAccountRoutes.CreateAccount> {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }

        is CreateAccountDestination.Back -> navigateUp()
    }
}