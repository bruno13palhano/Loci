package com.bruno13palhano.loci.ui.navigation

import androidx.navigation.NavController
import com.bruno13palhano.account.navigation.CreateAccountRoutes
import com.bruno13palhano.forgotpassword.navigation.ForgotPasswordRoutes
import com.bruno13palhano.home.navigation.HomeRoutes
import com.bruno13palhano.login.navigation.LoginRoutes
import com.bruno13palhano.login.ui.login.presenter.LoginDestination

internal fun NavController.loginNavigateTo(destination: LoginDestination) {
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