package com.misztal.tvshows.ui.details

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
import kotlinx.android.synthetic.main.item_similiar_show.view.*

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
class DetailsRecyclerAdapter : ArrayMvvmRecyclerAdapter<TvShow, DetailsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_similiar_show, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.title.text = item.name

        if (item.posterPath != null) {
            Picasso.with(holder.itemView.context)
                    .load("${MovieApi.imagePath(200)}${item.backdropPath}")
                    .placeholder(R.drawable.ic_poster)
                    .error(R.drawable.ic_error)
                    .into(holder.posterImage)
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val posterImage: ImageView = view.image
        val title: TextView = view.titleText

        init {
            view.setOnClickListener {
                onPositionClicked(adapterPosition)
            }
        }
    }

}