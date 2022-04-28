package de.awil1304.kittyapp.domain.use_case

import de.awil1304.kittyapp.common.Resource
import de.awil1304.kittyapp.data.remote.dto.toKitty
import de.awil1304.kittyapp.domain.model.Kitty
import de.awil1304.kittyapp.domain.repository.KittyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetKittiesUseCase @Inject constructor(
    private val repository: KittyRepository
) {
    operator fun invoke(): Flow<Resource<List<Kitty>>> = flow {
        try {
            emit(Resource.Loading<List<Kitty>>())
            val kitties = repository.getKitties().map {it.toKitty() }
            emit(Resource.Success<List<Kitty>>(kitties))
        } catch (e: HttpException) {
            emit (Resource.Error<List<Kitty>>(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit (Resource.Error<List<Kitty>>(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
        }

    }

}