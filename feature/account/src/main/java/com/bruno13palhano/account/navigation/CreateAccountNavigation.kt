package com.bruno13palhano.account.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bruno13palhano.account.ui.presenter.CreateAccountRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.createAccountScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    navigateBack: () -> Unit,
    showBottomMenu: (show: Boolean) -> Unit
) {
    composable<CreateAccountRoutes.CreateAccount> {
        showBottomMenu(false)
        CreateAccountRoute(
            modifier = modifier,
            navigateToHome = navigateToHome,
            navigateBack = navigateBack
        )
    }
}

sealed interface CreateAccountRoutes {
    @Serializable
    data object CreateAccount : CreateAccountRoutes
}