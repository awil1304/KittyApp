package de.awil1304.kittyapp.domain.model

/**
 * kitty object that is used to read into card composable
 */
data class Kitty(
    val id: String,
    val imageUrl: String,
    val title: String
)