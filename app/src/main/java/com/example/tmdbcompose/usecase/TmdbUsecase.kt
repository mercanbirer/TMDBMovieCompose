package com.example.tmdbcompose.usecase

import android.util.Log
import com.example.tmdbcompose.di.Resource
import com.example.tmdbcompose.model.Movie
import com.example.tmdbcompose.model.MovieDetail
import com.example.tmdbcompose.repository.TmdbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class TmdbUsecase @Inject constructor(
    private val repository: TmdbRepository
) {
    fun getMovie(): Flow<Resource<Movie>> = flow {
        try {
            emit(Resource.Loading())
            val users = withContext(Dispatchers.IO){
                repository.getMovie()
            }
            emit(Resource.Success(users))
        }catch (e:HttpException){
            Log.e("erorrrrrr", e.toString())
            emit(Resource.Error(e.localizedMessage ?:"An unexpected error occured"))
        }catch (e: IOException){
            Log.e("erorrrrrr", e.toString())
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
    fun getMovieDetail(layoutType: Long): Flow<Resource<MovieDetail>> = flow {
        try {
//            emit(Resource.Loading())
            val data = withContext(Dispatchers.IO) { repository.getMovieDetail(layoutType) }
            emit(Resource.Success(data = data))
            Timber.tag("data").e(data.toString())

        } catch (e: HttpException) {
//            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}