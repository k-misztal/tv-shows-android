package com.misztal.tvshows.ui.details

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.misztal.tvshows.ExceptionHandler
import com.misztal.tvshows.data.ShowsRepository
import com.misztal.tvshows.di.qualifier.IOScheduler
import com.misztal.tvshows.network.api.model.TvShow
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
class DetailViewStateFactory @Inject constructor(
        private val showsRepository: ShowsRepository,
        @IOScheduler private val ioScheduler: Scheduler,
        private val exceptionHandler: ExceptionHandler
) : ViewModelProvider.Factory {

    lateinit var show: TvShow

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(show, showsRepository, ioScheduler, exceptionHandler) as T
        }

        throw IllegalArgumentException()
    }

}