package de.awil1304.kittyapp.presentation

sealed class Screen(val route: String) {
    object KittyListScreen : Screen("kitty_list_screen")
}