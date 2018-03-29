package com.misztal.tvshows.di.module

import com.misztal.tvshows.ui.details.DetailsActivity
import com.misztal.tvshows.ui.home.HomeActivity
import com.misztal.tvshows.ui.shows.TvShowsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components withing the app
 *
 * @author Krzysztof Misztal
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun bindDetailsActivity(): DetailsActivity

    @ContributesAndroidInjector
    abstract fun bindTvShowsFragment(): TvShowsFragment
}