package com.example.tmdbcompose.usecase

import com.example.tmdbcompose.di.Resource
import com.example.tmdbcompose.model.MovieDetail
import com.example.tmdbcompose.repository.TmdbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class TmdbUsecase @Inject constructor(
    private val repository: TmdbRepository
) {
    fun getMovieDetail(movieId: Long): Flow<Resource<MovieDetail>> = flow {
        try {
//            emit(Resource.Loading())
            val data = withContext(Dispatchers.IO) {
                repository.getMovieDetail(movieId = movieId)
            }
            emit(Resource.Success(data = data))
        } catch (e: Exception) {
//            emit(Resource.Error("Error"))
        } catch (e: Exception) {
//            emit(Resource.Error("Error"))
        }

    }
}