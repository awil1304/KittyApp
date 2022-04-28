package de.awil1304.kittyapp.util

sealed class Screen(val route: String) {
    object KittyListScreen : Screen("kitty_list_screen")
}