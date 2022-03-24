package com.example.tmdbcompose.repository

import com.example.tmdbcompose.model.Movie
import com.example.tmdbcompose.model.MovieDetail
import com.example.tmdbcompose.service.TMDBApi
import javax.inject.Inject

class TmdbRepository @Inject constructor(
    private val api: TMDBApi
) : TMDBApi {

    override suspend fun getMovie(): Movie {
        return  api.getMovie()
    }

    override suspend fun getMovieDetail(movieId: Long): MovieDetail {
        return api.getMovieDetail(movieId = movieId)
    }
}