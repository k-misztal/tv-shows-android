package com.misztal.tvshows.ui.shows

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.misztal.tvshows.ExceptionHandler
import com.misztal.tvshows.data.ShowsRepository
import com.misztal.tvshows.di.qualifier.IOScheduler
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
class TvShowsViewModelFactory @Inject constructor(
        private val showsRepository: ShowsRepository,
        @IOScheduler private val ioScheduler: Scheduler,
        private val exceptionHandler: ExceptionHandler
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TvShowsViewModel::class.java)) {
            return TvShowsViewModel(showsRepository, ioScheduler, exceptionHandler) as T
        }

        throw IllegalArgumentException()
    }

}