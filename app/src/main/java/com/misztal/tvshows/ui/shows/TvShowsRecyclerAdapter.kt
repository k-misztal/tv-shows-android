package com.misztal.tvshows.ui.shows

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.misztal.tvshows.R
import com.misztal.tvshows.network.api.MovieApi
import com.misztal.tvshows.network.api.model.TvShow
import com.misztal.tvshows.ui.base.recycler.ArrayMvvmRecyclerAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_tv_show.view.*

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
class TvShowsRecyclerAdapter : ArrayMvvmRecyclerAdapter<TvShow, TvShowsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tv_show, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.title.text = item.name
        holder.rating.text = item.voteAverage.toString()

        if (item.posterPath != null) {
            Picasso.with(holder.itemView.context)
                    .load("${MovieApi.IMAGE_PATH}${item.posterPath}")
                    .into(holder.posterImage)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val posterImage: ImageView = view.poster
        val title: TextView = view.showTitle
        val rating: TextView = view.ratingText
    }
}