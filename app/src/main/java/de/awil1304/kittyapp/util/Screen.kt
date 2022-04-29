package de.awil1304.kittyapp.util

/**
 * screen registry for tidy navigation
 */
// helps deal with navigation (unnecessary here, but scalable)
sealed class Screen(val route: String) {
    object KittyListScreen : Screen("kitty_list_screen")
}