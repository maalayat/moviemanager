package ec.solmedia.themoviedb.view.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ec.solmedia.moviemanager.commons.InfiniteScrollListener
import ec.solmedia.themoviedb.R
import ec.solmedia.themoviedb.commons.extensions.inflate
import ec.solmedia.themoviedb.commons.extensions.snack
import ec.solmedia.themoviedb.model.Media
import ec.solmedia.themoviedb.model.MediaItem
import ec.solmedia.themoviedb.view.activity.MediaDetailActivity
import ec.solmedia.themoviedb.view.adapter.MediaAdapter
import ec.solmedia.themoviedb.view.feature.MediaManager
import kotlinx.android.synthetic.main.fragment_now_playing.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class NowPlayingFragment : RxBaseFragment() {

    private val TYPE: String = "now_playing"

    private val mediaManager by lazy {
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

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_MEDIA)) {
            media = savedInstanceState.get(KEY_MEDIA) as Media
            (rvMovies.adapter as MediaAdapter).clearAndAddMediaItems(media!!.mediaItems)
        } else {
            requestMovies()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val movies = (rvMovies.adapter as MediaAdapter).getMediaItems()
        if (media != null && movies.isNotEmpty()) {
            outState.putParcelable(KEY_MEDIA, media?.copy(mediaItems = movies))
        }
        super.onSaveInstanceState(outState)
    }

    private fun requestMovies() {
        val subscription = mediaManager
                .get(TYPE, media?.page ?: 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrieveMovies ->
                            media = retrieveMovies
                            (rvMovies.adapter as MediaAdapter).addMediaItems(retrieveMovies.mediaItems)
                        },
                        { e -> view?.snack(e.localizedMessage) {} }
                )

        subscriptions.add(subscription)
    }

    private fun setupRecyclerView() {
        rvMovies.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ requestMovies() }, linearLayout))
        }
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
