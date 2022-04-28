package de.awil1304.kittyapp.data.remote.dto

import de.awil1304.kittyapp.domain.model.Kitty

// object that api returns
data class KittyDto(
    val id: String,
    val imageUrl: String,
    val title: String
)

// function
fun KittyDto.toKitty(): Kitty {
    return Kitty(
        id = id,
        imageUrl = imageUrl.replace("http://", "https://"),
        title = title.replaceFirstChar { it.uppercase() }
    )
}