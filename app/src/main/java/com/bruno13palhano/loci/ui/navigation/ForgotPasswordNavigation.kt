package com.bruno13palhano.loci.ui.navigation

import androidx.navigation.NavController
import com.bruno13palhano.forgotpassword.navigation.ForgotPasswordRoutes
import com.bruno13palhano.forgotpassword.ui.presenter.ForgotPasswordDestination

internal fun NavController.forgotPasswordNavigateTo(destination: ForgotPasswordDestination) {
    when (destination) {
        is ForgotPasswordDestination.Home -> navigate(route = ForgotPasswordRoutes.ForgotPassword)

        is ForgotPasswordDestination.Back -> navigateUp()
    }
}