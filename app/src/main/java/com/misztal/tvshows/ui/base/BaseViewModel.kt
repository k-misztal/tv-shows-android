package com.misztal.tvshows.ui.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Base ViewModel class.
 *
 * @author Krzysztof Misztal
 */
abstract class BaseViewModel<T : ViewState> : ViewModel() {

    val stateData: MutableLiveData<T> = MutableLiveData()
    val disposables = CompositeDisposable()

    protected fun postState(state: T?) {
        stateData.postValue(state)
    }

    protected fun getState(): T? = stateData.value

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}