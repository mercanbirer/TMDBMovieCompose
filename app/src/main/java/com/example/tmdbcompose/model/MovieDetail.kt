package com.example.tmdbcompose.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class MovieDetail(
    @SerializedName("id")
    val workerId: Long = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("poster_path")
    var posterPath: String? = "",
    @SerializedName("backdrop_path")
    val backdropPath: String? = "",
    @SerializedName("overview")
    val overview: String? = "",
    @SerializedName("popularity")
    val popularity: Double? = 0.0,
    @SerializedName("release_date")
    val releaseDate: String? = "",
    @SerializedName("vote_average")
    val voteAverage: Double? = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int? = 0,
    @SerializedName("runtime")
    val runtime: Int? = 0,
    @SerializedName("tagline")
    val tagline: String? = "",
    @SerializedName("imdb_id")
    val imdbId: String? = ""
): java.io.Serializable
