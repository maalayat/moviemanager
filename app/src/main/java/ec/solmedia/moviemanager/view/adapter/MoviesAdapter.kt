package ec.solmedia.moviemanager.view.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ec.solmedia.moviemanager.commons.adapter.AdapterConstants
import ec.solmedia.moviemanager.commons.adapter.ViewType
import ec.solmedia.moviemanager.commons.adapter.ViewTypeDelegateAdapter
import ec.solmedia.moviemanager.model.Movie


class MoviesAdapter(listener: (Movie) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType(): Int = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.MOVIES, MoviesDelegateAdapter(listener))
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }

    fun addMovies(movies: List<Movie>) {
        val initPosition = items.size - 1
        items.addAll(initPosition, movies)
        notifyItemRangeInserted(initPosition, initPosition + movies.size)
    }

    //For Orientation Change
    fun getMovies(): List<Movie> {
        return items
                .filter { it.getViewType() == AdapterConstants.MOVIES }
                .map { it as Movie }
    }
}