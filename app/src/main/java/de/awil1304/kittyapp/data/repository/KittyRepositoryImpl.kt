package de.awil1304.kittyapp.data.repository

import de.awil1304.kittyapp.data.remote.KittyApi
import de.awil1304.kittyapp.data.remote.dto.KittyDto
import de.awil1304.kittyapp.domain.model.Kitty
import de.awil1304.kittyapp.domain.repository.KittyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//unnecessary here, but clean and scalable
class KittyRepositoryImpl @Inject constructor(
    private val api: KittyApi
) : KittyRepository {

    override suspend fun getKitties(): List<KittyDto> {
        return api.getKitties()
    }
}