package ec.solmedia.themoviedb.view.fragment


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ec.solmedia.moviemanager.commons.InfiniteScrollListener
import ec.solmedia.themoviedb.R
import ec.solmedia.themoviedb.TheMovieDBApp
import ec.solmedia.themoviedb.commons.extensions.inflate
import ec.solmedia.themoviedb.commons.extensions.snack
import ec.solmedia.themoviedb.model.Media
import ec.solmedia.themoviedb.view.adapter.MediaAdapter
import ec.solmedia.themoviedb.view.feature.MediaManager
import kotlinx.android.synthetic.main.fragment_media.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject


class MediaFragment : RxBaseFragment() {

    val adapter = MediaAdapter { navigateToMediaDetail(it) }
    @Inject lateinit var mediaManager: MediaManager
    private lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TheMovieDBApp.mediaComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        type = arguments.getString(EXTRA_TYPE)
        return container?.inflate(R.layout.fragment_media)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()
        initAdapter()

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_MEDIA)) {
            media = savedInstanceState.get(KEY_MEDIA) as Media
            adapter.clearAndAddMediaItems(media!!.mediaItems)
        } else {
            requestMovies()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val items = adapter.getMediaItems()
        if (media != null && items.isNotEmpty()) {
            outState.putParcelable(KEY_MEDIA, media?.copy(mediaItems = items))
        }
        super.onSaveInstanceState(outState)
    }

    private fun requestMovies() {
        val subscription = mediaManager
                .get(type, media?.page ?: 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrieveMovies ->
                            media = retrieveMovies
                            adapter.addMediaItems(retrieveMovies.mediaItems)
                        },
                        { e -> view?.snack(e.localizedMessage) {} }
                )

        subscriptions.add(subscription)
    }

    private fun setupRecyclerView() {
        rvMedia.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ requestMovies() }, linearLayout))
        }
    }

    private fun initAdapter() {
        if (rvMedia.adapter == null) {
            rvMedia.adapter = adapter
        }
    }
}
