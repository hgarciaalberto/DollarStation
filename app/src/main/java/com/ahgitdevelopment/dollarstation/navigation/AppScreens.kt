package com.ahgitdevelopment.dollarstation.navigation

sealed class AppScreens(val route: String) {
    object DashboardScreen : AppScreens("DashboardScreen")
    object FirestoreScreen : AppScreens("FirestoreScreen")
}
