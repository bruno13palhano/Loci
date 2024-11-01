package com.bruno13palhano.loci.ui.menu

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Workspaces
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bruno13palhano.home.navigation.HomeRoutes
import com.bruno13palhano.loci.R
import com.bruno13palhano.messages.navigation.MessagesRoutes
import com.bruno13palhano.profile.navigation.ProfileRoutes
import com.bruno13palhano.workspace.navigation.WorkspaceRoutes

@Composable
fun BottomMenu(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val items = listOf(
        Screen.Home,
        Screen.Workspace,
        Screen.Messages,
        Screen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(modifier = modifier) {
        items.forEach { screen ->
            val selected = currentDestination?.isRouteSelected(screen = screen)

            NavigationBarItem(
                selected = selected == true,
                onClick = {
                    navController.navigate(route = screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = stringResource(id = screen.resourceId)
                    )
                },
                label = { Text(text = stringResource(id = screen.resourceId)) }
            )
        }
    }
}

fun <T>NavDestination.isRouteSelected(screen: Screen<T>): Boolean {
    return hierarchy.any {
        it.route?.split(".")?.lastOrNull() == screen.route.toString()
    }
}

sealed class Screen<T>(
    val route: T,
    val icon: ImageVector,
    @StringRes val resourceId: Int
) {
    data object Home : Screen<HomeRoutes>(
        route = HomeRoutes.Home,
        icon = Icons.Filled.Home,
        resourceId = R.string.home
    )

    data object Workspace : Screen<WorkspaceRoutes>(
        route = WorkspaceRoutes.Workspace,
        icon = Icons.Filled.Workspaces,
        resourceId = R.string.workspace
    )

    data object Messages : Screen<MessagesRoutes>(
        route = MessagesRoutes.Messages,
        icon = Icons.AutoMirrored.Filled.Message,
        resourceId = R.string.messages
    )

    data object Profile : Screen<ProfileRoutes>(
        route = ProfileRoutes.Profile,
        icon = Icons.Filled.Person,
        resourceId = R.string.profile
    )
}