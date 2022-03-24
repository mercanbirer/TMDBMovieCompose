package com.example.tmdbcompose.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tmdbcompose.viewmodel.TmdbViewModel

@Composable
fun SerieScreen(
    viewModel: TmdbViewModel = hiltViewModel()
){

    Column {
        Text(text = "MERCAN")
    }
}