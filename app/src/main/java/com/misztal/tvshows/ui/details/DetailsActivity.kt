package com.misztal.tvshows.ui.details

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.misztal.tvshows.R
import com.misztal.tvshows.network.api.MovieApi
import com.misztal.tvshows.network.api.model.TvShow
import com.misztal.tvshows.ui.base.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
class DetailsActivity : BaseActivity<DetailsViewState, DetailsViewModel>() {

    @Inject
    lateinit var vmFactory: DetailViewStateFactory

    private val show by lazy { intent.getSerializableExtra(KEY_SHOW) as TvShow }

    private val adapter = DetailsRecyclerAdapter()

    companion object {
        private const val KEY_SHOW = "show"

        fun newIntent(context: Context, show: TvShow): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(KEY_SHOW, show)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        title = ""

        loadShowInfo()
    }

    override fun render(state: DetailsViewState) {
        if (state.isLoading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }

        if (state.didDownloadFailed) {
            retryDownload.visibility = View.VISIBLE
        } else {
            retryDownload.visibility = View.GONE
        }

        adapter.setItems(state.similarShows)
    }

    override fun renderEmptyState() {
        progressBar.visibility = View.GONE
        retryDownload.visibility = View.VISIBLE
        adapter.setItems(emptyList())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    //==========================================================================
    // private
    //==========================================================================

    private fun loadShowInfo() {
        titleText.text = show.name
        description.text = show.overview
        firstAirDate.text = show.firstAirDate

        this.rating.text = getString(R.string.rating, show.voteAverage, show.voteCount)
        this.ratingBar.rating = show.voteAverage

        with(Picasso.with(this)) {
            load("${MovieApi.imagePath(500)}${show.backdropPath}")
                    .placeholder(R.drawable.ic_poster)
                    .error(R.drawable.ic_error)
                    .into(image)
            load("${MovieApi.imagePath(200)}${show.posterPath}")
                    .placeholder(R.drawable.ic_poster)
                    .error(R.drawable.ic_error).into(poster)
        }

        retryDownload.setOnClickListener { viewModel.fetchSimilarShows() }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapter.onItemClickedListener = { item, _ ->
            val intent = newIntent(this, item)
            startActivity(intent)
        }
    }

    //==========================================================================

    override fun createViewModel(): DetailsViewModel {
        vmFactory.show = show
        return ViewModelProviders.of(this, vmFactory).get(DetailsViewModel::class.java)
    }
}