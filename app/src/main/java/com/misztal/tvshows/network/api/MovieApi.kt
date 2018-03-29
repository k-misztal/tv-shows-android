package com.misztal.tvshows.network.api

import com.misztal.tvshows.network.api.model.response.TvShows
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
interface MovieApi {

    companion object {
        const val IMAGE_PATH = "http://image.tmdb.org/t/p/w185"
    }

    @GET("tv/popular")
    fun getPopularTvShows(@Query("page") page: Int): Single<TvShows>

}