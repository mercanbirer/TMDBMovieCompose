package com.example.tmdbcompose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbcompose.di.Resource
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

    private val tmdbResponse = MutableStateFlow<Resource<MovieDetail>>(Resource.Idle())

    fun getMovieDetail(movieId: Long) {
        viewModelScope.launch {
            usecase.getMovieDetail(movieId = movieId).collect { result ->
                when (result) {
                    is Resource.Loading ->{
                        tmdbResponse.value = Resource.Loading()
                    }
                    is Resource.Success ->{
                        tmdbResponse.value = Resource.Success(result.data!!)
                        Timber.tag("sendDocument ").e(result.data.toString())

                }
                    is Resource.Error ->{
                        tmdbResponse.value = Resource.Error("error")
                    }
                    is Resource.Idle ->{
                        tmdbResponse.value = Resource.Idle()
                    }
                }
            }
        }
    }
}