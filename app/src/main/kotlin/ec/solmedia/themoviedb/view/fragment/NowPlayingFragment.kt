package ec.solmedia.themoviedb.view.fragment


import android.content.Intent
import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_now_playing.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class NowPlayingFragment : RxBaseFragment() {

    private val TYPE: String = "now_playing"

    private val movieManager by lazy {
        MediaManager()
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
                .get(TYPE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrieveMovies -> (rvMovies.adapter as MediaAdapter).addMovies(retrieveMovies) },
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
            rvMovies.adapter = MediaAdapter {
                navigateToMovieDetail(it)
            }
        }
    }

    private fun navigateToMovieDetail(mediaItem: MediaItem) {
        val intent: Intent = Intent(context, MediaDetailActivity::class.java)
        intent.putExtra(MediaDetailActivity.EXTRA_MEDIA_ITEM, mediaItem)
        startActivity(intent)
    }
}
