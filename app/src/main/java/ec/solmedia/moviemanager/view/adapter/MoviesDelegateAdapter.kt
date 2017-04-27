package ec.solmedia.moviemanager.view.adapter

import android.support.v4.widget.SearchViewCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ec.solmedia.moviemanager.R
import ec.solmedia.moviemanager.commons.adapter.ViewType
import ec.solmedia.moviemanager.commons.adapter.ViewTypeDelegateAdapter
import ec.solmedia.moviemanager.commons.extensions.inflate
import ec.solmedia.moviemanager.commons.extensions.loadImg
import ec.solmedia.moviemanager.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*


class MoviesDelegateAdapter(val listener: (Movie) -> Unit) : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return MoviesViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as MoviesViewHolder
        holder.bind(item as Movie, listener)
    }

    class MoviesViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_movie)) {

        fun bind(item: Movie, listener: (Movie) -> Unit) = with(itemView) {
            ivMovieImage.loadImg(item.posterPath)
            tvTitle.text = item.title
            tvOverView.text = item.overView
            setOnClickListener { listener(item) }
        }
    }

}