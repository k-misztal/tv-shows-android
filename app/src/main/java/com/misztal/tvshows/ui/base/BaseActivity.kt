package com.misztal.tvshows.ui.base

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
abstract class BaseActivity<S : ViewState, out VM : BaseViewModel<S>> : AppCompatActivity() {

    val viewModel: VM by lazy { createViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel.stateData.observe(this, Observer<S> { renderInternal(it) })
    }

    open fun renderError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    //==========================================================================
    // Abstract
    //==========================================================================

    /**
     * Render state. Error message is handled by base class, so you don't have to handle it here.
     * To change rendering of the error message override []
     */
    abstract fun render(state: S)

    /**
     * Render empty state when VM returned null. That shouldn't be called too often, but LiveData
     * can return null (unlike RxStreams) so we want to keep null safety here.
     */
    abstract fun renderEmptyState()

    /**
     * Function that creates view model. **Should use [android.arch.lifecycle.ViewModelProviders]!**
     */
    abstract fun createViewModel(): VM

    //==========================================================================
    // Private
    //==========================================================================

    private fun renderInternal(state: S?) {
        Timber.d("Rendering state: $state")
        val errorMessage = state?.errorMessage
        if (errorMessage != null) {
            renderError(getString(errorMessage))
        }

        if (state != null) {
            render(state)
        } else {
            renderEmptyState()
        }
    }
}