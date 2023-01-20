package com.chuhelan.microxinas.dotchat.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chuhelan.microxinas.dotchat.navigation.graphs.NavigationBarNavGraph

/**
 * @Author: Hxina
 * @Date: 2023/01/19 15:10
 * @Description: 应用导航
 */
@Composable
fun DotChatNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = DotChatDestinationGraph.NAVIGATION_BAR_ROUTE,
        route = DotChatDestinationGraph.ROOT_ROUTE
    ) {
        composable(route = DotChatDestinationGraph.NAVIGATION_BAR_ROUTE) {
            NavigationBarNavGraph()
        }
    }
}