package ec.solmedia.themoviedb.view.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ec.solmedia.themoviedb.R
import ec.solmedia.themoviedb.commons.extensions.inflate
import ec.solmedia.themoviedb.commons.extensions.snack
import ec.solmedia.themoviedb.model.MediaItem
import ec.solmedia.themoviedb.view.activity.MediaDetailActivity
import ec.solmedia.themoviedb.view.adapter.MediaAdapter
import ec.solmedia.themoviedb.view.feature.MediaManager
import kotlinx.android.synthetic.main.fragment_upcoming.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * A simple [Fragment] subclass.
 */
class UpcomingFragment : RxBaseFragment() {

    private val TYPE: String = "upcoming"

    private val movieManager by lazy {
        MediaManager()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_upcoming)
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
                .get(TYPE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrieveMovies -> (rvUpMovies.adapter as MediaAdapter).addMovies(retrieveMovies) },
                        { e -> view?.snack(e.localizedMessage) {} }
                )

        subscriptions.add(subscription)
    }

    private fun setupRecyclerView() {
        rvUpMovies.setHasFixedSize(true)
        rvUpMovies.layoutManager = LinearLayoutManager(context)
    }

    private fun initAdapter() {
        if (rvUpMovies.adapter == null) {
            rvUpMovies.adapter = MediaAdapter { movie ->
                navigateToMovieDetail(movie)
            }
        }
    }

    private fun navigateToMovieDetail(mediaItem: MediaItem) {
        val intent: Intent = Intent(context, MediaDetailActivity::class.java)
        intent.putExtra(MediaDetailActivity.EXTRA_MEDIA_ITEM, mediaItem)
        startActivity(intent)
    }
}
