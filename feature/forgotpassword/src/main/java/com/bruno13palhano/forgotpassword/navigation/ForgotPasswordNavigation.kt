package com.bruno13palhano.forgotpassword.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bruno13palhano.forgotpassword.ui.presenter.ForgotPasswordDestination
import com.bruno13palhano.forgotpassword.ui.presenter.ForgotPasswordRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.forgotPasswordScreen(
    modifier: Modifier = Modifier,
    navigateTo: (destination: ForgotPasswordDestination) -> Unit,
    showBottomMenu: (show: Boolean) -> Unit
) {
    composable<ForgotPasswordRoutes.ForgotPassword> {
        showBottomMenu(false)
        ForgotPasswordRoute(
            modifier = modifier,
            navigateTo = navigateTo,
        )
    }
}

sealed interface ForgotPasswordRoutes {
    @Serializable
    data object ForgotPassword : ForgotPasswordRoutes
}