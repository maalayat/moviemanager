package ec.solmedia.moviemanager.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ec.solmedia.moviemanager.R
import ec.solmedia.moviemanager.commons.adapter.ViewType
import ec.solmedia.moviemanager.commons.adapter.ViewTypeDelegateAdapter
import ec.solmedia.moviemanager.commons.extensions.inflate


class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder = LoadingViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {}

    class LoadingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_loading))
}