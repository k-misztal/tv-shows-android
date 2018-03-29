package com.misztal.tvshows.ui.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
abstract class BaseViewModel<T : ViewState> : ViewModel() {

    val stateData: MutableLiveData<T> = MutableLiveData()

}