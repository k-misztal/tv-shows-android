package com.misztal.tvshows.network.api

import com.misztal.tvshows.network.api.model.response.TvShows
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
interface MovieApi {

    companion object {
        fun imagePath(width: Int) = "http://image.tmdb.org/t/p/w$width"
    }

    @GET("discover/tv?sort_by=popularity.desc")
    fun getPopularTvShows(@Query("page") page: Int): Single<TvShows>

    @GET("tv/{id}/similar")
    fun getSimilarShows(@Path("id") id: Int, @Query("page") page: Int): Single<TvShows>

}