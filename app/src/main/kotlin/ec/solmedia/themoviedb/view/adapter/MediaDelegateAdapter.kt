package ec.solmedia.themoviedb.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ec.solmedia.themoviedb.R
import ec.solmedia.themoviedb.TheMovieDBApp
import ec.solmedia.themoviedb.commons.adapter.ViewType
import ec.solmedia.themoviedb.commons.adapter.ViewTypeDelegateAdapter
import ec.solmedia.themoviedb.commons.extensions.inflate
import ec.solmedia.themoviedb.commons.extensions.loadImg
import ec.solmedia.themoviedb.model.MediaItem
import kotlinx.android.synthetic.main.item_media.view.*


class MediaDelegateAdapter(val listener: (MediaItem) -> Unit) : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return MoviesViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as MoviesViewHolder
        holder.bind(item as MediaItem, listener)
    }

    class MoviesViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_media)) {

        fun bind(item: MediaItem, listener: (MediaItem) -> Unit) = with(itemView) {
            ivMediaImage.loadImg(
                    item.posterPath,
                    TheMovieDBApp.PATH_POSTER,
                    resources.getDimensionPixelSize(R.dimen.poster_width),
                    resources.getDimensionPixelSize(R.dimen.poster_height))
            tvTitle.text = item.title ?: item.name
            tvReleaseDate.text = item.releaseDate ?: item.firstAirDate
            tvOverView.text = item.overView
            tvVoteAverage.text = item.voteAverage.toString()
            tvVoteCount.text = item.voteCount.toString()
            setOnClickListener { listener(item) }
        }
    }

}