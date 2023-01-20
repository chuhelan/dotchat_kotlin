package com.chuhelan.microxinas.dotchat.navigation.graphs

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chuhelan.microxinas.dotchat.R
import com.chuhelan.microxinas.dotchat.navigation.DotChatDestinationGraph
import com.chuhelan.microxinas.dotchat.ui.components.DotChatNavigationBar
import com.chuhelan.microxinas.dotchat.ui.components.NavigationBarItems

/**
 * @Author: Hxina
 * @Date: 2023/01/19 18:14
 * @Description: 应用底部导航栏 Navigation bar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBarNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            DotChatNavigationBar(navHostController = navHostController)
        }
    ) { paddingValues ->
        NavHost(
            modifier = modifier.padding(paddingValues),
            navController = navHostController,
            startDestination = NavigationBarItems.Home.route,
            route = DotChatDestinationGraph.NAVIGATION_BAR_ROUTE
        ) {
            composable(route = NavigationBarItems.Home.route) {
                Text(text = stringResource(id = R.string.navigation_bar_home))
            }
            composable(route = NavigationBarItems.Explore.route) {
                Text(text = stringResource(id = R.string.navigation_bar_explore))
            }
            composable(route = NavigationBarItems.Notifications.route) {
                Text(text = stringResource(id = R.string.navigation_bar_notifications))
            }
            composable(route = NavigationBarItems.Profile.route) {
                Text(text = stringResource(id = R.string.navigation_bar_profile))
            }
        }
    }
}