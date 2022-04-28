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

// use case to get coins from api (should probably be in data layer?)
class GetKittiesUseCase @Inject constructor(

    // inject interface, not impl so it's easily replace
    private val repository: KittyRepository
) {

    // flow for resource values over time for network request
    operator fun invoke(): Flow<Resource<List<Kitty>>> = flow {
        try {
            // for progress bar in ui
            emit(Resource.Loading<List<Kitty>>())

            // map List<KittyDto> to List<Kitty>
            val kitties = repository.getKitties().map { it.toKitty() }
            emit(Resource.Success<List<Kitty>>(kitties))

            // unsuccessful response
        } catch (e: HttpException) {
            emit(Resource.Error<List<Kitty>>(e.localizedMessage ?: "An unexpected error occurred."))

            // exception for no internet connection
        } catch (e: IOException) {
            emit(
                Resource.Error<List<Kitty>>(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )
        }

    }

}