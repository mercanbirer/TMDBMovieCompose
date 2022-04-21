package com.example.tmdbcompose.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbcompose.di.Resource
import com.example.tmdbcompose.model.Movie
import com.example.tmdbcompose.model.MovieDetail
import com.example.tmdbcompose.model.Series
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

    private val _apiSerie = MutableStateFlow<Resource<Series>>(Resource.Idle())
    var apiSerie: StateFlow<Resource<Series>> = _apiSerie

    fun getMovie() {
        viewModelScope.launch {
            usecase.getMovie().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _apiMovie.value = Resource.Loading()
                        Timber.tag("Loading").e("")
                    }
                    is Resource.Success -> {
                        _apiMovie.value = Resource.Success(result.data!!)
                        Timber.tag("Success").e("")
                    }
                    is Resource.Error -> {
                        _apiMovie.value = Resource.Error("error")
                        Timber.tag("Error").e("")
                    }
                    is Resource.Idle -> {
                        _apiMovie.value = Resource.Idle()
                    }
                }
            }
        }
    }

    fun getSeries() {
        viewModelScope.launch {
            usecase.getSeries().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _apiSerie.value = Resource.Loading()
                        Timber.tag("Loading").e("")
                    }
                    is Resource.Success -> {
                        _apiSerie.value = Resource.Success(result.data!!)
                        Timber.tag("Success").e("")
                    }
                    is Resource.Error -> {
                        _apiSerie.value = Resource.Error("error")
                        Timber.tag("Error").e("")
                    }
                    is Resource.Idle -> {
                        _apiSerie.value = Resource.Idle()
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