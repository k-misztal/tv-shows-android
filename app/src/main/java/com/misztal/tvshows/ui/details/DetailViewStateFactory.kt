package com.misztal.tvshows.ui.details

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.misztal.tvshows.data.DataManager
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
        private val dataManager: DataManager,
        @IOScheduler private val ioScheduler: Scheduler
) : ViewModelProvider.Factory {

    lateinit var show: TvShow

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(show, dataManager, ioScheduler) as T
        }

        throw IllegalArgumentException()
    }

}