package ec.solmedia.themoviedb.view.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ec.solmedia.moviemanager.commons.InfiniteScrollListener
import ec.solmedia.themoviedb.R
import ec.solmedia.themoviedb.TheMovieDBApp
import ec.solmedia.themoviedb.commons.extensions.inflate
import ec.solmedia.themoviedb.domain.RequestMediaCommand
import ec.solmedia.themoviedb.model.Media
import ec.solmedia.themoviedb.model.MediaItem
import ec.solmedia.themoviedb.view.activity.MediaDetailActivity
import ec.solmedia.themoviedb.view.adapter.MediaAdapter
import kotlinx.android.synthetic.main.fragment_media.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject


class MediaFragment : Fragment() {

    @Inject lateinit var request: RequestMediaCommand
    private lateinit var category: String
    private lateinit var mediaType: String

    private val adapter = MediaAdapter { navigateToMediaDetail(it) }
    private var media: Media? = null

    companion object {
        val KEY_MEDIA = "media"
        val KEY_TITLE = "title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupInjection()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        category = arguments.getString(FragmentProvider.EXTRA_CATEGORY)
        mediaType = arguments.getString(FragmentProvider.EXTRA_MEDIA_TYPE)
        return container?.inflate(R.layout.fragment_media)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()
        setupAdapter()

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_TITLE)) {
            activity.title = savedInstanceState.get(KEY_TITLE) as CharSequence?
        }

        //TODO fix saveInstance
        requestMovies()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY_TITLE, activity.title.toString())

        super.onSaveInstanceState(outState)
    }

    private fun requestMovies() {
        val actualPage = media?.page ?: 0
        val totalPage = media?.totalPages ?: actualPage + 1
        Log.d("MediaFragment", "actualpage: $actualPage totalPage: $totalPage")

        doAsync {
            if (actualPage < totalPage) {
                val result = request.execute(mediaType, category, actualPage)
                media = result
                result?.let {
                    //control null
                    uiThread {
                        adapter.addMediaItems(result.mediaItems)
                    }
                }
            } else {
                uiThread {
                    adapter.removeLoadingItem()
                    Log.d("MediaFragment ", "Remove Loading Item")
                }
            }
        }
    }

    private fun setupInjection() {
        TheMovieDBApp.graph.plus().inject(this)
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

    private fun setupAdapter() {
        if (rvMedia.adapter == null) {
            rvMedia.adapter = adapter
        }
    }

    protected fun navigateToMediaDetail(mediaItem: MediaItem) {
        val intent: Intent = Intent(context, MediaDetailActivity::class.java)
        intent.putExtra(MediaDetailActivity.EXTRA_TITLE, mediaItem.title ?: mediaItem.name)
        intent.putExtra(MediaDetailActivity.EXTRA_OVERVIEW, mediaItem.overView)
        intent.putExtra(MediaDetailActivity.EXTRA_BACK_DROP, mediaItem.backDropPath)
        intent.putExtra(MediaDetailActivity.EXTRA_POSTER, mediaItem.posterPath)
        intent.putExtra(MediaDetailActivity.EXTRA_VOTE_AVERAGE, mediaItem.voteAverage)
        intent.putExtra(MediaDetailActivity.EXTRA_VOTE_COUNT, mediaItem.voteCount)
        intent.putExtra(MediaDetailActivity.EXTRA_RELEASE_DATE, mediaItem.releaseDate ?: mediaItem.firstAirDate)
        intent.putExtra(MediaDetailActivity.EXTRA_ORIGINAL_NAME, mediaItem.originalTitle ?: mediaItem.originalName)
        intent.putExtra(MediaDetailActivity.EXTRA_POPULARITY, mediaItem.popularity)

        startActivity(intent)
    }
}
