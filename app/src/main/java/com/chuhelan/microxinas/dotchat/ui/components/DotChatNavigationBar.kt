package com.chuhelan.microxinas.dotchat.ui.components

import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.chuhelan.microxinas.dotchat.R

/**
 * @Author: Hxina
 * @Date: 2023/01/18 14:21
 * @Description: 应用主界面底部导航栏
 */
@Composable
fun DotChatNavigationBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    val labelItems = listOf(
        NavigationBarItems.Home,
        NavigationBarItems.Explore,
        NavigationBarItems.Notifications,
        NavigationBarItems.Profile
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentNavBarDestination = labelItems.any { it.route == currentDestination?.route }

    if (currentNavBarDestination) {
        NavigationBar {
            labelItems.forEach { item ->
                val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        navHostController.popBackStack()
                        navHostController.navigate(item.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Crossfade(
                            targetState = selected,
                            animationSpec = tween(500)
                        ) { selected ->
                            if (selected) {
                                Icon(
                                    imageVector = item.activeIcon,
                                    contentDescription = stringResource(id = item.label),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            } else {
                                Icon(
                                    imageVector = item.inactiveIcon,
                                    contentDescription = stringResource(id = item.label)
                                )
                            }
                        }
                    },
                    modifier = modifier,
                    label = {
                        if (selected) {
                            Text(
                                text = stringResource(id = item.label),
                                color = MaterialTheme.colorScheme.primary
                            )
                        } else {
                            Text(text = stringResource(id = item.label))
                        }
                    },
                    alwaysShowLabel = true
                )
            }
        }
    }
}

/**
 * 导航栏项
 * @param[route] 导航路线
 * @param[activeIcon] 选中时展示的图标
 * @param[inactiveIcon] 未选中时展示的图标
 * @param[label] 导航栏项的标签文本
 */
sealed class NavigationBarItems(
    val route: String,
    val activeIcon: ImageVector,
    val inactiveIcon: ImageVector,
    @StringRes val label: Int
) {
    object Home : NavigationBarItems(
        route = "home",
        activeIcon = Icons.Rounded.Home,
        inactiveIcon = Icons.Outlined.Home,
        label = R.string.navigation_bar_home
    )

    object Explore : NavigationBarItems(
        route = "explore",
        activeIcon = Icons.Rounded.Explore,
        inactiveIcon = Icons.Outlined.Explore,
        label = R.string.navigation_bar_explore
    )

    object Notifications : NavigationBarItems(
        route = "notifications",
        activeIcon = Icons.Rounded.Notifications,
        inactiveIcon = Icons.Outlined.Notifications,
        label = R.string.navigation_bar_notifications
    )

    object Profile : NavigationBarItems(
        route = "profile",
        activeIcon = Icons.Rounded.Person,
        inactiveIcon = Icons.Outlined.Person,
        label = R.string.navigation_bar_profile
    )
}