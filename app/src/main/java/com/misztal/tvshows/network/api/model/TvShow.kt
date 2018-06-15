package com.misztal.tvshows.network.api.model

import java.io.Serializable

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
data class TvShow(
        val id: Int,
        val posterPath: String?,
        val backdropPath: String?,
        val popularity: Float,
        val voteAverage: Float,
        val voteCount: Int,
        val firstAirDate: String, //it's well formatted, we don't need date object
        val name: String,
        val originalName: String,
        val overview: String
) : Serializable