package com.example.tmdbcompose.repository

import com.example.tmdbcompose.service.TMDBApi
import javax.inject.Inject

class TmdbRepository @Inject constructor(
    private val api : TMDBApi
) {

}