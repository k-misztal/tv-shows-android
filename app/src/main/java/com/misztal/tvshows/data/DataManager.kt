package com.misztal.tvshows.data

import com.misztal.tvshows.network.api.MovieApi
import com.misztal.tvshows.network.api.model.response.TvShows
import io.reactivex.Single
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

    fun getPopularTvShows(page: Int, fromYear: Int, toYear: Int): Single<TvShows> {
        val from = "$fromYear-01-01"
        val to = "$toYear-12-31"
        return movieApi.getPopularTvShows(page, from, to)
    }

    fun getSimilarShows(id: Int, page: Int) = movieApi.getSimilarShows(id, page)
}