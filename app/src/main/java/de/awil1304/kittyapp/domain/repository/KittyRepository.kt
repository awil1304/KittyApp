package de.awil1304.kittyapp.domain.repository

import de.awil1304.kittyapp.data.remote.dto.KittyDto
import de.awil1304.kittyapp.domain.model.Kitty
import kotlinx.coroutines.flow.Flow

interface KittyRepository {

    suspend fun getKitties(): List<KittyDto>

}