package com.misztal.tvshows.network.api

import com.misztal.tvshows.network.api.model.response.PopularTvShows
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
interface MovieApi {

    @GET("/tv/popular")
    fun getPopularTvShows(@Query("page") page: Int): Single<PopularTvShows>

}