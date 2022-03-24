package com.example.tmdbcompose.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tmdbcompose.R
import com.example.tmdbcompose.bundle.BundleKeys
import com.example.tmdbcompose.di.Resource
import com.example.tmdbcompose.model.MovieDetail
import com.example.tmdbcompose.viewmodel.TmdbViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun MovieScreen(
    navController: NavController?,
    viewModel: TmdbViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit, block = {
        viewModel.getMovie()
    })
    val tmdbResponse = viewModel.tmdbResponse.collectAsState()
    val movie = viewModel.apiMovie.collectAsState()


        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (movie.value) {
                    is Resource.Loading -> {
                        Log.e("deneme","load")
                        CircularProgressIndicator(color = colorResource(id = R.color.black))
                    }
                    is Resource.Success -> {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                          item {
                              val list = movie.value.data
                              Log.e("deneme", list.toString())

                          }
                        }
                    }
                    is Error -> {
                        Timber.tag("error").e("")

                        Text(
                            text = movie.value.message!!,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            textAlign = TextAlign.Center,
                            color = colorResource(id = R.color.purple_200)
                        )
                    }
                }
            }
        }
    }


