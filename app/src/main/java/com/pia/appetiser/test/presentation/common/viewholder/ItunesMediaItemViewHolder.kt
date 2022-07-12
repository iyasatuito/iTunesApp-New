package com.pia.appetiser.test.presentation.common.viewholder

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pia.appetiser.test.R
import com.pia.appetiser.test.data.di.GlideApp
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import kotlinx.android.synthetic.main.item_itunes.view.*

class ItunesMediaItemViewHolder(
    itemView: View,
    private val onItunesItemClicked: (DisplayableItunesDetails) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private var itunesDataView: DisplayableItunesDetails? = null

    class Factory {
        fun create(parent: ViewGroup, onItunesItemClicked: (DisplayableItunesDetails) -> Unit) =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_itunes, parent, false)
                .let { ItunesMediaItemViewHolder(it, onItunesItemClicked) }
    }

    init {
        itemView.setOnClickListener {
            itunesDataView?.let { onItunesItemClicked.invoke(it) }
        }
    }

    fun bind(item: DisplayableItunesDetails) {
        this.itunesDataView = item
        itemView.itunes_title.text = item.trackName
        itemView.itunes_rating.text = "Rating: " + (item.contentAdvisoryRating ?: "Not Available")

        GlideApp.with(itemView)
            .load(item.itemImageUrl)
            .placeholder(R.drawable.media_placeholder)
            .error(R.drawable.media_placeholder)
            .into(itemView.itunes_image)
    }
}