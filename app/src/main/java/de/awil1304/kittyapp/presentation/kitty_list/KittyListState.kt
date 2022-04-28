package de.awil1304.kittyapp.presentation.kitty_list

import de.awil1304.kittyapp.domain.model.Kitty

data class KittyListState(
    val isLoading: Boolean = false,
    val kittyList: List<Kitty> = emptyList<Kitty>().toMutableList(),
    val error: String = ""
)