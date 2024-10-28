package com.bruno13palhano.profile.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bruno13palhano.profile.ui.presenter.ProfileRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.profileScreen(
    modifier: Modifier = Modifier,
    showBottomMenu: (show: Boolean) -> Unit
) {
    composable<ProfileRoutes.Profile> {
        showBottomMenu(true)
        ProfileRoute(modifier = modifier)
    }
}

sealed interface ProfileRoutes {
    @Serializable
    data object Profile : ProfileRoutes
}