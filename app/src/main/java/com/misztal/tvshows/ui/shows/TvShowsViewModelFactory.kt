package com.misztal.tvshows.ui.shows

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.misztal.tvshows.data.DataManager
import javax.inject.Inject

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
class TvShowsViewModelFactory @Inject constructor(
        private val dataManager: DataManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TvShowsViewModel::class.java)) {
            return TvShowsViewModel(dataManager) as T
        }

        throw IllegalArgumentException()
    }

}