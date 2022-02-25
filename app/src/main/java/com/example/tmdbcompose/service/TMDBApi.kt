package com.example.tmdbcompose.service

import com.example.tmdbcompose.model.MovieDetail
import retrofit2.http.Path

interface TMDBApi {
    //https://api.themoviedb.org/3/movie/550?api_key=cc17eb7820fb50a9fb7a92bc0ca676d9
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Long
    ): MovieDetail
}