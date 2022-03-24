package com.example.tmdbcompose.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbcompose.di.Resource
import com.example.tmdbcompose.model.Movie
import com.example.tmdbcompose.model.MovieDetail
import com.example.tmdbcompose.usecase.TmdbUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TmdbViewModel @Inject constructor(
    private val usecase: TmdbUsecase
) : ViewModel() {

    private val _tmdbResponse = MutableStateFlow<Resource<MovieDetail>>(Resource.Idle())
    val tmdbResponse: StateFlow<Resource<MovieDetail>> = _tmdbResponse

    private val _apiMovie = MutableStateFlow<Resource<Movie>>(Resource.Idle())
    var apiMovie: StateFlow<Resource<Movie>> = _apiMovie


    fun getMovie() {
        viewModelScope.launch {
            usecase.getMovie().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _apiMovie.value = Resource.Loading()
                        Log.e("loading", "")
                    }
                    is Resource.Success -> {
                        _apiMovie.value = Resource.Success(result.data!!)

                        Log.e("success", result.data.toString())
                    }
                    is Resource.Error -> {
                        _apiMovie.value = Resource.Error("error")
                        Log.e("error", "")

                    }
                    is Resource.Idle -> {
                        _apiMovie.value = Resource.Idle()
                    }
                }
            }
        }
    }

    fun getMovieDetail(layoutType: Long) {
        viewModelScope.launch {
            usecase.getMovieDetail(layoutType = layoutType).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _tmdbResponse.value = Resource.Loading()
                    }
                    is Resource.Success -> {
                        _tmdbResponse.value = Resource.Success(result.data!!)
                        Timber.tag("sendDocument ").e(result.data.toString())
                    }
                    is Resource.Error -> {
                        _tmdbResponse.value = Resource.Error("error")
                        Timber.tag("error ").e("")


                    }
                    is Resource.Idle -> {
                        _tmdbResponse.value = Resource.Idle()
                    }
                }
            }
        }
    }
}