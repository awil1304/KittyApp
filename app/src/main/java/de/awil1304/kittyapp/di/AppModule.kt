package de.awil1304.kittyapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.awil1304.kittyapp.common.Constants
import de.awil1304.kittyapp.data.remote.KittyApi
import de.awil1304.kittyapp.data.repository.KittyRepositoryImpl
import de.awil1304.kittyapp.domain.repository.KittyRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// di to avoid hard coding implementations, so they can be replaced and easily tested

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideKittyApi(): KittyApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KittyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideKittyRepository(api: KittyApi): KittyRepository {
        return KittyRepositoryImpl(api)
    }
}