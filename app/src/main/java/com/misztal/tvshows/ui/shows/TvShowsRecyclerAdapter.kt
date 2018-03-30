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
class TvShowsRecyclerAdapter : ArrayMvvmRecyclerAdapter<TvShow, RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM_TYPE_NORMAL = 0
        const val ITEM_TYPE_LOADING = 1
    }

    var isLoading = false
        set(value) {
            if (value == field) return

            field = value
            if (value) {
                notifyItemInserted(data.size)
            } else {
                notifyItemRemoved(data.size)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_TYPE_NORMAL) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tv_show, parent, false)
            ViewHolder(view)

        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val item = data[position]

            holder.title.text = item.name
            holder.rating.text = item.voteAverage.toString()

            if (item.posterPath != null) {
                Picasso.with(holder.itemView.context)
                        .load("${MovieApi.imagePath(200)}${item.posterPath}")
                        .placeholder(R.drawable.ic_poster)
                        .error(R.drawable.ic_error)
                        .into(holder.posterImage)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position > data.size - 1) {
            return ITEM_TYPE_LOADING
        }

        return ITEM_TYPE_NORMAL
    }

    override fun getItemCount(): Int {
        if (!isLoading)
            return super.getItemCount()

        return super.getItemCount() + 1
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val posterImage: ImageView = view.poster
        val title: TextView = view.showTitle
        val rating: TextView = view.ratingText

        init {
            view.setOnClickListener {
                onPositionClicked(adapterPosition)
            }
        }
    }

    class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view)
}