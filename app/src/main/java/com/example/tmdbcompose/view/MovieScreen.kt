package com.example.tmdbcompose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tmdbcompose.R
import com.example.tmdbcompose.bundle.BundleKeys
import com.example.tmdbcompose.model.MovieDetail
import com.example.tmdbcompose.viewmodel.TmdbViewModel

@Composable
fun MovieScreen(
    navController: NavController?,
    viewModel: TmdbViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        LaunchedEffect(Unit, block = {
        })

    }
}

