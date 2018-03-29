package com.misztal.tvshows.ui.details

import com.misztal.tvshows.data.DataManager
import com.misztal.tvshows.network.api.model.TvShow
import com.misztal.tvshows.ui.base.BaseViewModel
import io.reactivex.Scheduler

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
class DetailsViewModel(
        private val show: TvShow,
        private val dataManager: DataManager,
        private val ioScheduler: Scheduler

) : BaseViewModel<DetailsViewState>() {
}