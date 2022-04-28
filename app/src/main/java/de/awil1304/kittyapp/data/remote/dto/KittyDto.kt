package de.awil1304.kittyapp.data.remote.dto

import androidx.compose.ui.text.capitalize
import de.awil1304.kittyapp.domain.model.Kitty

data class KittyDto(
    val id: String,
    val imageUrl: String,
    val title: String
)

fun KittyDto.toKitty(): Kitty {
    return Kitty(
        id = id,
        imageUrl = imageUrl.replace("http://", "https://"),
        title = title.replaceFirstChar { it.uppercase() }
    )
}