package de.awil1304.kittyapp.data.remote

import de.awil1304.kittyapp.data.remote.dto.KittyDto
import retrofit2.http.GET

//Api request
interface KittyApi {

    @GET("https://thaliabooks.herokuapp.com/cats")
    suspend fun getKitties(): List<KittyDto>

}