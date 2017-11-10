package ec.solmedia.themoviedb.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ec.solmedia.themoviedb.R
import ec.solmedia.themoviedb.VimoApp
import ec.solmedia.themoviedb.commons.adapter.ViewType
import ec.solmedia.themoviedb.commons.adapter.ViewTypeDelegateAdapter
import ec.solmedia.themoviedb.commons.extensions.inflate
import ec.solmedia.themoviedb.commons.extensions.loadImg
import ec.solmedia.themoviedb.model.MediaItem
import kotlinx.android.synthetic.main.item_media.view.*


class MediaDelegateAdapter(private val itemClick: (MediaItem) -> Unit) : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) =
            MediaViewHolder(parent, itemClick)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as MediaViewHolder
        holder.bind(item as MediaItem)
    }

    class MediaViewHolder(parent: ViewGroup, private val itemClick: (MediaItem) -> Unit) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_media)) {

        fun bind(item: MediaItem) = with(itemView) {
            ivMediaImage.loadImg(
                    item.posterPath,
                    VimoApp.PATH_POSTER,
                    resources.getDimensionPixelSize(R.dimen.poster_width),
                    resources.getDimensionPixelSize(R.dimen.poster_height))
            tvTitle.text = item.title ?: item.name
            tvReleaseDate.text = item.releaseDate ?: item.firstAirDate
            tvOverView.text = item.overView
            tvVoteAverage.text = item.voteAverage.toString()
            tvVoteCount.text = item.voteCount.toString()
            setOnClickListener { itemClick(item) }
        }
    }

}