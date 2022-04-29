package de.awil1304.kittyapp.domain.repository

import de.awil1304.kittyapp.common.Resource
import de.awil1304.kittyapp.data.remote.dto.KittyDto
import de.awil1304.kittyapp.domain.model.Kitty

class FakeKittyRepository : KittyRepository {

    private val kitties = emptyList<KittyDto>()
    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getKitties(): List<KittyDto> {
        TODO("Not yet implemented")
//        return if(shouldReturnNetworkError) {
//            Resource.Error("Error", null)
//        }
    }
}