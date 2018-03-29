package com.misztal.tvshows.ui.shows

import com.misztal.tvshows.ExceptionHandler
import com.misztal.tvshows.data.DataManager
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
class TvShowsViewModel(
        private val dataManager: DataManager,
        private val ioScheduler: Scheduler,
        private val exceptionHandler: ExceptionHandler
) : BaseViewModel<TvShowsState>() {

    private var lastFetchedPage = 0
    private var totalPages = Int.MAX_VALUE
    private var isDownloading = false

    init {
        // fetch first page when vm is created
        postState(TvShowsState(emptyList(), true, false, null))
        fetchNextPage()
    }

    fun fetchNextPage() {
        if (isDownloading) {
            Timber.w("Already fetching page. Task cancelled.")
            return
        }

        if (lastFetchedPage >= totalPages) {
            Timber.e("No more pages to download. Task cancelled.")
            return
        }

        isDownloading = true
        val toFetch = lastFetchedPage + 1

        val isLoading = toFetch <= 1
        val isLoadingNextPage = !isLoading

        val state = TvShowsState(getShows(), isLoading, isLoadingNextPage)
        postState(state)

        val disposable = dataManager.getPopularTvShows(toFetch)
                .subscribeOn(ioScheduler)
                .subscribeBy(
                        onSuccess = this::onPageFetched,
                        onError = {
                            Timber.e(it, "Error fetching tv series.")

                            isDownloading = false
                            val msg = exceptionHandler.getErrorMessage(it)

                            val newState = TvShowsState(getShows(), false, false, msg)
                            postState(newState)
                        }
                )
        addDisposable(disposable)
    }

    private fun onPageFetched(tvShows: TvShows) {
        isDownloading = false
        lastFetchedPage = tvShows.page
        totalPages = tvShows.totalPages

        val shows = getShows() + tvShows.results
        postState(TvShowsState(shows, false, false))
    }

    private fun getShows(): List<TvShow> = getState()?.shows.orEmpty()

}