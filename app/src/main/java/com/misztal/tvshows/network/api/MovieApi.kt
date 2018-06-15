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

    /**
     * @param page page to be fetched.
     * @param from from date. In a format: yyyy-MM-dd, for instance 2017-08-11
     * @param to to date.
     */
    @GET("discover/tv?sort_by=popularity.desc")
    fun getPopularTvShows(@Query("page") page: Int, @Query("air_date.gte") from: String,
                          @Query("air_date.lte") to: String): Single<TvShows>

    @GET("tv/{id}/similar")
    fun getSimilarShows(@Path("id") id: Int, @Query("page") page: Int): Single<TvShows>

}