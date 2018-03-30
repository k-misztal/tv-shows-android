package com.misztal.tvshows.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.misztal.tvshows.BuildConfig
import com.misztal.tvshows.network.HttpClientFactory
import com.misztal.tvshows.network.api.MovieApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient = HttpClientFactory.create()

    @Provides
    fun provideGson(): Gson {
        val builder = GsonBuilder()
        builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)

        return builder.create()
    }

    @Provides
    fun provideRestService(client: OkHttpClient, gson: Gson): MovieApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.MOVIE_API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create(MovieApi::class.java)
    }


}
