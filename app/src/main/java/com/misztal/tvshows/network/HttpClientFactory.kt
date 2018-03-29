package com.misztal.tvshows.network

import com.misztal.tvshows.BuildConfig
import okhttp3.OkHttpClient

/**
 * Create configured [OkHttpClient].
 */
object HttpClientFactory {

    private const val QUERY_API_KEY = "api_key"

    fun create(): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .addInterceptor({ chain ->
                    val builder = chain.request().newBuilder()
                    val url = chain.request().url().newBuilder()
                            .addEncodedQueryParameter(QUERY_API_KEY, BuildConfig.MOVIE_API_KEY)
                            .build()

                    val request = builder.url(url).build()

                    return@addInterceptor chain.proceed(request)
                })

        return builder.build()
    }
}