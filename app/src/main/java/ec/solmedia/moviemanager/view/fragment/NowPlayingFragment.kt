package ec.solmedia.moviemanager.view.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ec.solmedia.moviemanager.R
import ec.solmedia.moviemanager.commons.extensions.inflate
import ec.solmedia.moviemanager.commons.extensions.snack
import ec.solmedia.moviemanager.model.Movie
import ec.solmedia.moviemanager.view.activity.MovieDetailActivity
import ec.solmedia.moviemanager.view.adapter.MoviesAdapter
import ec.solmedia.moviemanager.view.feature.MovieManager
import kotlinx.android.synthetic.main.fragment_now_playing.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class NowPlayingFragment : RxBaseFragment() {

    /*private val rvListMovies by lazy {
        rvMovies
    }*/
    private val movieManager by lazy {
        MovieManager()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_now_playing)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()
        initAdapter()

        if (savedInstanceState == null) {
            requestMovies()
        }
    }

    private fun requestMovies() {
        val subscription = movieManager
                .getNowPlayingMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retriveMovies -> (rvMovies.adapter as MoviesAdapter).addMovies(retriveMovies) },
                        { e -> view?.snack(e.localizedMessage) {} }
                )

        subscriptions.add(subscription)
    }


    private fun setupRecyclerView() {
        rvMovies.setHasFixedSize(true)
        rvMovies.layoutManager = LinearLayoutManager(context)
    }

    private fun initAdapter() {
        if (rvMovies.adapter == null) {
            rvMovies.adapter = MoviesAdapter {
                navigateToMovieDetail(it)
            }
        }
    }

    private fun navigateToMovieDetail(movie: Movie) {
        val intent: Intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie)
        startActivity(intent)
    }
}
