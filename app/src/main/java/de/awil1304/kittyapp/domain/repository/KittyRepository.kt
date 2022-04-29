package de.awil1304.kittyapp.domain.repository

import de.awil1304.kittyapp.common.Resource
import de.awil1304.kittyapp.data.remote.dto.KittyDto

/**
 * interface for function for api request
 */
// interface makes it easily testable
interface KittyRepository {
    suspend fun getKitties(): List<KittyDto>

}