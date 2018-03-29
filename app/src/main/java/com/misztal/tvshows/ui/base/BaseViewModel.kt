package com.misztal.tvshows.ui.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Base ViewModel class.
 *
 * @author Krzysztof Misztal
 */
abstract class BaseViewModel<T : ViewState> : ViewModel() {

    val stateData: MutableLiveData<T> = MutableLiveData()

}