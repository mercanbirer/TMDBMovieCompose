package com.example.tmdbcompose.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.tmdbcompose.R
import com.example.tmdbcompose.view.MovieScreen
import com.example.tmdbcompose.view.SerieScreen

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var icon: Int, var title: String, var screen: ComposableFun) {
    object Movies : TabItem(icon = R.drawable.movie, title = "Movies", screen = { MovieScreen(null) })
    object Series : TabItem(icon = R.drawable.serie, title = "Series", screen = { SerieScreen() })
}
