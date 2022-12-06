package com.ahgitdevelopment.dollarstation.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahgitdevelopment.dollarstation.features.dashboard.DashboardScreen
import com.ahgitdevelopment.dollarstation.features.firestore.FirestoreScreen
import com.ahgitdevelopment.dollarstation.navigation.BottomNavItem.Companion.items
import com.ahgitdevelopment.dollarstation.ui.theme.DollarStationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
//        topBar = {
//            TopAppBar(
//                navController = navController,
//                title = AppScreens.DashboardScreen.route
//            )
//        },
        bottomBar = { BottomNavigation(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreens.DashboardScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = AppScreens.DashboardScreen.route) {
                DashboardScreen(navController)
            }
            composable(route = AppScreens.FirestoreScreen.route) {
                FirestoreScreen(navController)
            }
//        composable(
//            route = AppScreens.ScreenB.route + "/{text}",
//            arguments = listOf(navArgument(name = "text") {
//                type = NavType.StringType
//            })
//        ) {
//            AppScreens.ScreenB(navController, it.arguments?.getString("text"))
//        }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(navController: NavController, title: String) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
        }
    )
}

@Composable
fun BottomNavigation(navController: NavController) {

    val selectedItem by remember { mutableStateOf(items[0]) }

    NavigationBar {

        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        modifier = Modifier.height(35.dp),
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 9.sp
                    )
                },
                selected = selectedItem == item,
                onClick = {
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppNavigationPreview() {
    DollarStationTheme {
        AppNavigation()
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    DollarStationTheme {
        TopAppBar(rememberNavController(), "Title")
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    DollarStationTheme {
        BottomNavigation(rememberNavController())
    }
}