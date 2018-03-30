package com.misztal.tvshows.ui.details

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
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
    }

    override fun renderEmptyState() {
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

        this.rating.text = getString(R.string.rating, show.voteAverage, show.voteCount)
        this.ratingBar.rating = show.voteAverage

        with(Picasso.with(this)) {
            load("${MovieApi.imagePath(500)}${show.backdropPath}").into(image)
            load("${MovieApi.imagePath(200)}${show.posterPath}").into(poster)
        }
    }

    //==========================================================================

    override fun createViewModel(): DetailsViewModel {
        vmFactory.show = show
        return ViewModelProviders.of(this, vmFactory).get(DetailsViewModel::class.java)
    }
}