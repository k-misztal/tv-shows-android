package com.misztal.tvshows.ui.shows

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.transition.TransitionManager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.misztal.tvshows.R
import com.misztal.tvshows.ui.base.BaseFragment
import com.misztal.tvshows.ui.details.DetailsActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_tv_shows.*
import timber.log.Timber
import javax.inject.Inject


/**
 * Fragment that displays the most popular tv shows
 *
 * @author Krzysztof Misztal
 */
class TvShowsFragment : BaseFragment<TvShowsState, TvShowsViewModel>() {

    @Inject
    lateinit var vmFactory: TvShowsViewModelFactory

    lateinit var adapter: TvShowsRecyclerAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TvShowsRecyclerAdapter()
        layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        recyclerView.addOnScrollListener(scrollListener)

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.reset()
        }

        adapter.onItemClickedListener = { show, _ ->
            val context = context
            if (context == null) {
                Timber.e("Error! Context is null!")
            } else {
                val intent = DetailsActivity.newIntent(context, show)
                startActivity(intent)
            }
        }
    }

    //==========================================================================
    // Render state
    //==========================================================================

    override fun render(state: TvShowsState) {
        TransitionManager.beginDelayedTransition(view as ViewGroup)
        swipeRefreshLayout.isRefreshing = state.isLoading

        if (state.shows.isEmpty() && !state.isLoading) {
            noShowsText.visibility = View.VISIBLE
        } else {
            noShowsText.visibility = View.GONE
        }

        adapter.setItems(state.shows)
        adapter.isLoading = state.isLoadingNextPage
        if (adapter.isLoading) {
            recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
        }
    }

    override fun renderEmptyState() {
        TransitionManager.beginDelayedTransition(view as ViewGroup)
        noShowsText.visibility = View.VISIBLE
        swipeRefreshLayout.isRefreshing = false
        adapter.setItems(emptyList())
    }

    //==========================================================================

    override fun createViewModel(): TvShowsViewModel =
            ViewModelProviders.of(this, vmFactory).get(TvShowsViewModel::class.java)

    //==========================================================================

    private val scrollListener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (!recyclerView.canScrollVertically(1)) {
                viewModel.fetchNextPage()
            }
        }
    }
}