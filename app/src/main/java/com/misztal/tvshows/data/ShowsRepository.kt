package com.misztal.tvshows.data

import com.misztal.tvshows.network.api.MovieApi
import com.misztal.tvshows.network.api.model.response.TvShows
import io.reactivex.Single
import javax.inject.Inject

/**
 * Aggregates all sources to access shows.
 *
 * @author Krzysztof Misztal
 */
class ShowsRepository @Inject constructor(
        private val movieApi: MovieApi
) {

    fun getPopularTvShows(page: Int, fromYear: Int, toYear: Int): Single<TvShows> {
        val from = "$fromYear-01-01"
        val to = "$toYear-12-31"
        return movieApi.getPopularTvShows(page, from, to)
    }

    fun getSimilarShows(id: Int, page: Int) = movieApi.getSimilarShows(id, page)
}