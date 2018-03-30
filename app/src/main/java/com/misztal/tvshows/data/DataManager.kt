package com.misztal.tvshows.data

import com.misztal.tvshows.network.api.MovieApi
import javax.inject.Inject

/**
 * Aggregates all data sources. It may seem redundant, because it's very simple app.
 *
 * In more complicated examples this class aggregates all data sources, like data base, api,
 * shared preferences and so on and so forth.
 *
 * @author Krzysztof Misztal
 */
class DataManager @Inject constructor(
        private val movieApi: MovieApi
) {

    fun getPopularTvShows(page: Int) = movieApi.getPopularTvShows(page)

    fun getSimilarShows(id: Int, page: Int) = movieApi.getSimilarShows(id, page)
}