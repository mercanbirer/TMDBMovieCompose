package com.example.tmdbcompose.di

import com.example.tmdbcompose.repository.TmdbRepository
import com.example.tmdbcompose.service.TMDBApi
import com.example.tmdbcompose.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCryptoRepository(
        api: TMDBApi
    ) = TmdbRepository(api = api)

    @Singleton
    @Provides
    fun provideCryptoApi(): TMDBApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(TMDBApi::class.java)
    }
}