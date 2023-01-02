package com.ahgitdevelopment.dollarstation.navigation

sealed class AppScreens(val route: String) {
    object DashboardScreen : AppScreens("dashboard")

    object HistoryScreen : AppScreens("history/{currency}") {
        const val CURRENCY_KEY = "currency"
        fun createRoute(currency: String) = "history/$currency"
    }

    object FirestoreScreen : AppScreens("FirestoreScreen")

    object Api2Screen : AppScreens("Api2")

    object CalculatorScreen : AppScreens("Calculator")
}
