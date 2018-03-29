package com.misztal.tvshows.ui.details

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.misztal.tvshows.R
import com.misztal.tvshows.network.api.model.TvShow
import com.misztal.tvshows.ui.base.BaseActivity
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
        private val KEY_SHOW = "show"

        fun newIntent(context: Context, show: TvShow): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(KEY_SHOW, show)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = show.name
    }

    override fun render(state: DetailsViewState) {
    }

    override fun renderEmptyState() {
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun createViewModel(): DetailsViewModel {
        vmFactory.show = show
        return ViewModelProviders.of(this, vmFactory).get(DetailsViewModel::class.java)
    }
}