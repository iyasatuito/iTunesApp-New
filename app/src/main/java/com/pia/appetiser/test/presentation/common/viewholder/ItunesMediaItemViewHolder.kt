package com.pia.appetiser.test.presentation.common.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pia.appetiser.test.R
import com.pia.appetiser.test.data.di.GlideApp
import com.pia.appetiser.test.presentation.common.adapter.ItunesMediaAdapter
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import com.pia.appetiser.test.presentation.util.getScreenWidth
import com.pia.appetiser.test.presentation.util.pixelsToSp
import kotlinx.android.synthetic.main.item_itunes.view.*

class ItunesMediaItemViewHolder(
    itemView: View,
    private val callback: ItunesMediaAdapter.Delegate
) : BindingViewHolder<DisplayableItunesDetails>(itemView) {

    private var itunesDataView: DisplayableItunesDetails? = null

    class Factory(private val delegate: ItunesMediaAdapter.Delegate) :
        ViewHolderFactory<BindingViewHolder<DisplayableItunesDetails>> {
        override fun create(parent: ViewGroup) = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_itunes, parent, false)
            .let { ItunesMediaItemViewHolder(it, delegate)}
    }

    init {
        itemView.setOnClickListener {
            itunesDataView?.let { callback.onItunesItemClicked(it) }
        }
    }

    override fun bind(item: DisplayableItunesDetails) {
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