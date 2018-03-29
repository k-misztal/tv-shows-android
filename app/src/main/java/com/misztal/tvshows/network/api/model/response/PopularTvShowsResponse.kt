package com.misztal.tvshows.network.api.model.response

import com.misztal.tvshows.network.api.model.PopularTvShow

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
data class PopularTvShowsResponse(
        val page: Int,
        val totalResults: Int,
        val totalPages: Int,
        val results: List<PopularTvShow>
)