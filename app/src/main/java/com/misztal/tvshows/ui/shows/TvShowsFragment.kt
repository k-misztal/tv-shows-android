package com.misztal.tvshows.ui.shows

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.misztal.tvshows.R
import com.misztal.tvshows.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Fragment that displays the most popular tv shows
 *
 * @author Krzysztof Misztal
 */
class TvShowsFragment : BaseFragment<TvShowsState, TvShowsViewModel>() {

    @Inject
    lateinit var vmFactory: TvShowsViewModelFactory

    companion object {
        fun newInstance(): TvShowsFragment = TvShowsFragment()
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_tv_shows, container, false)
    }

    //==========================================================================
    // Render state
    //==========================================================================

    override fun render(state: TvShowsState) {

    }

    override fun renderEmptyState() {

    }

    //==========================================================================

    override fun createViewModel(): TvShowsViewModel = vmFactory.create(TvShowsViewModel::class.java)
}