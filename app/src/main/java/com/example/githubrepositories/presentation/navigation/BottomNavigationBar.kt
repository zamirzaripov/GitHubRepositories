package com.example.githubrepositories.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.githubrepositories.presentation.screen.RepoDetailsScreen
import com.example.githubrepositories.presentation.screen.RepoListScreen
import com.example.githubrepositories.presentation.screen.Screen2
import com.example.githubrepositories.presentation.screen.Screen3
import com.example.githubrepositories.presentation.screen.Screen4
import com.example.githubrepositories.presentation.screen.Screen5

@Composable
fun BottomNavigationBar() {
    val navController = rememberNavController()
    val items = listOf(
        NavItem.Repositories,
        NavItem.Screen2Item,
        NavItem.Screen3Item,
        NavItem.Screen4Item,
        NavItem.Screen5Item,
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = Color.Black, contentColor = Color.LightGray) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = item.iconId),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp)
                            )
                        },
                        label = { Text(text = stringResource(id = item.titleId)) },
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }

                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavItem.Repositories.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavItem.Repositories.route) { RepoListScreen(navController) }
            composable(NavItem.Screen2Item.route) { Screen2() }
            composable(NavItem.Screen3Item.route) { Screen3() }
            composable(NavItem.Screen4Item.route) { Screen4() }
            composable(NavItem.Screen5Item.route) { Screen5() }
            composable("repoDetailsScreen/{id}"){ backStackEntry ->
                val id = backStackEntry.arguments?.getString("id") ?: ""
                RepoDetailsScreen(id, navController)
            }
        }
    }
}
