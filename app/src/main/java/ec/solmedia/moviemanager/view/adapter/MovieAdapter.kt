package ec.solmedia.moviemanager.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import ec.solmedia.moviemanager.R
import ec.solmedia.moviemanager.commons.extensions.inflate
import ec.solmedia.moviemanager.commons.extensions.loadImg
import ec.solmedia.moviemanager.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*


class MovieAdapter(val movies: List<Movie>, val listener: (Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHoler>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoler {
        return ViewHoler(parent.inflate(R.layout.item_movie))
    }

    override fun onBindViewHolder(holder: ViewHoler, position: Int) = with(holder.itemView) {
        val currentMovie = movies[position]
        ivMovieImage.loadImg(currentMovie.posterPath)
        tvTitle.text = currentMovie.title
        tvOverView.text = currentMovie.overView
        setOnClickListener { listener(currentMovie) }
    }

    override fun getItemCount() = movies.size

    class ViewHoler(view: View) : RecyclerView.ViewHolder(view)
}