package com.ahgitdevelopment.dollarstation.navigation

import androidx.annotation.DrawableRes
import com.ahgitdevelopment.dollarstation.R

sealed class BottomNavItem(
    var title: String,
    @DrawableRes var icon: Int,
    var screen_route: String
) {

    object Dashboard :
        BottomNavItem("API", R.drawable.api, AppScreens.DashboardScreen.route)

    object Firestore :
        BottomNavItem("Firestore", R.drawable.database, AppScreens.FirestoreScreen.route)

    object Api2 :
        BottomNavItem("Api2", R.drawable.api, AppScreens.Api2.route)

    companion object {
        val items = listOf(
            Dashboard,
            Firestore,
            Api2
        )
    }
}
