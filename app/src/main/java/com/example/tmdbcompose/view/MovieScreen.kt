package com.example.tmdbcompose.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tmdbcompose.R
import com.example.tmdbcompose.di.Resource
import com.example.tmdbcompose.viewmodel.TmdbViewModel
import timber.log.Timber

@Composable
fun MovieScreen(
    navController: NavController?,
    viewModel: TmdbViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit, block = {
        viewModel.getMovie()
    })
    val tmdbResponse by viewModel.tmdbResponse.collectAsState()
    val movie by viewModel.apiMovie.collectAsState()


        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (movie) {
                    is Resource.Loading -> {
                        Log.e("deneme","load")
                        CircularProgressIndicator(color = colorResource(id = R.color.black))
                    }
                    is Resource.Success -> {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            val list = movie.data!!.results
                            items(list.size){
                                Text(text = list[it].title,
                                    fontSize = 20.sp,
                                    style = typography.h5,
                                    fontFamily = FontFamily.Default,
                                    fontWeight = FontWeight.Bold,)
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }
                    }
                    is Error -> {
                        Timber.tag("error").e("")

                        Text(
                            text = movie.message!!,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            textAlign = TextAlign.Center,
                            color = colorResource(id = R.color.purple_200)
                        )
                    }
                    else -> {}
                }
            }
        }
    }


