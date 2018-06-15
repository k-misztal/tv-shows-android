package com.misztal.tvshows.ui.details

import com.misztal.tvshows.ExceptionHandler
import com.misztal.tvshows.data.ShowsRepository
import com.misztal.tvshows.network.api.model.TvShow
import com.misztal.tvshows.network.api.model.response.TvShows
import com.misztal.tvshows.ui.base.BaseViewModel
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
class DetailsViewModel(
        private val show: TvShow,
        private val showsRepository: ShowsRepository,
        private val ioScheduler: Scheduler,
        private val exceptionHandler: ExceptionHandler
) : BaseViewModel<DetailsViewState>() {

    init {
        fetchSimilarShows()
    }

    fun fetchSimilarShows() {
        disposables.clear()
        postState(DetailsViewState(emptyList(), true, false))

        val disposable = showsRepository.getSimilarShows(show.id, 1)
                .subscribeOn(ioScheduler)
                .subscribeBy(
                        onSuccess = this::onShowsFetched,
                        onError = {
                            Timber.e(it, "Error fetching tv series.")
                            val msg = exceptionHandler.getErrorMessage(it)
                            postState(DetailsViewState(emptyList(), false, true, msg))
                        }
                )
        addDisposable(disposable)
    }

    private fun onShowsFetched(shows: TvShows) {
        postState(DetailsViewState(shows.results, false, false))
    }

}