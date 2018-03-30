package com.misztal.tvshows.ui.details

import com.misztal.tvshows.network.api.model.TvShow
import com.misztal.tvshows.ui.base.ViewState

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
data class DetailsViewState(
        val similarShows: List<TvShow>,
        val isLoading: Boolean,
        val didDownloadFailed: Boolean,
        override val errorMessage: Int? = null
) : ViewState