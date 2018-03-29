package com.misztal.tvshows.network.api.model.response

import com.misztal.tvshows.network.api.model.TvShow

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
data class TvShows(
        val page: Int,
        val totalResults: Int,
        val totalPages: Int,
        val results: List<TvShow>
)