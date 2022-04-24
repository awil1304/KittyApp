package de.awil1304.kittyapp.domain.repository

import de.awil1304.kittyapp.data.remote.dto.KittyDto

interface KittyRepository {

    suspend fun getKitties(): List<KittyDto>
}