package com.example.tmdbcompose.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.tmdbcompose.di.AppModule
import com.example.tmdbcompose.di.Resource
import com.example.tmdbcompose.util.Constants
import com.example.tmdbcompose.util.NetworkImage
import com.example.tmdbcompose.viewmodel.TmdbViewModel
import com.google.accompanist.imageloading.ImageLoadState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import kotlinx.serialization.ExperimentalSerializationApi
import timber.log.Timber

@OptIn(ExperimentalSerializationApi::class)
@Composable
fun MovieScreen(
    navController: NavController?,
    viewModel: TmdbViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit, block = {
        viewModel.getMovie()
    })
    val movie by viewModel.apiMovie.collectAsState()
    val appModule = AppModule()
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (movie) {
                is Resource.Loading -> {
                    CircularProgressIndicator(color = colorResource(id = R.color.black))
                }
                is Resource.Success -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        val dataList = movie.data!!.results
                        items(dataList.size) {
                            Card {
                                NetworkImage(url = "${Constants.BASE_IMAGE_URL_API}${dataList[it].posterPath}") { painter, imageLoadState ->
                                    Image(
                                        painter = painter,
                                        contentDescription = "",
                                        contentScale = ContentScale.FillBounds,
                                        modifier = Modifier
                                            .padding(top = 10.dp, start = 10.dp)
                                            .height(95.dp).width(90.dp)
                                            .placeholder(
                                                visible = imageLoadState is ImageLoadState.Empty || imageLoadState is ImageLoadState.Loading,
                                                highlight = PlaceholderHighlight.shimmer(),
                                                color = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
                                            )
                                    )
                                }


                                Text(
                                    text = dataList[it].title,
                                    fontSize = 18.sp,
                                    style = typography.h5,
                                    fontFamily = FontFamily.Default,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(top = 45.dp, start = 120.dp)
                                )
                            }
                        }
                    }
                }
                is Error -> {
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
                else -> {
                }
            }
        }
    }
}


