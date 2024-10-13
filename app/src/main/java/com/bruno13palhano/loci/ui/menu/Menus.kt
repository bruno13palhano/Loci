package com.bruno13palhano.loci.ui.menu

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
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
import com.bruno13palhano.loci.R
import com.bruno13palhano.login.navigation.LoginRoutes

@Composable
fun BottomMenu(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val items = listOf(Screen.Login)

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
    data object Login : Screen<LoginRoutes>(
        route = LoginRoutes.Login,
        icon = Icons.AutoMirrored.Filled.Login,
        resourceId = R.string.app_name
    )
}