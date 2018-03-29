package com.misztal.tvshows.network.api

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
interface MovieApi {

    @GET("/tv/popular")
    fun getPopularTvShows(@Query("page") page: Int)

}