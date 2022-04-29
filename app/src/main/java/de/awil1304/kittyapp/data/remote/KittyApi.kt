package de.awil1304.kittyapp.data.remote

import de.awil1304.kittyapp.data.remote.dto.KittyDto
import retrofit2.http.GET

/**
 * interface for api request
 */
interface KittyApi {

    /**
     * gets all kitty dto objects from api
     */
    @GET("https://thaliabooks.herokuapp.com/cats")
    suspend fun getKitties(): List<KittyDto>

}