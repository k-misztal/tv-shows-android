package com.misztal.tvshows.ui.shows

import com.misztal.tvshows.network.api.model.TvShow
import com.misztal.tvshows.ui.base.ViewState

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
data class TvShowsState(
        val shows: List<TvShow>,
        val isLoading: Boolean,
        val isLoadingNextPage: Boolean,

        override val errorMessage: Int? = null
) : ViewState