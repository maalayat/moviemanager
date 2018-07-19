package ec.solmedia.themoviedb.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ec.solmedia.themoviedb.R
import ec.solmedia.themoviedb.VimoApp
import ec.solmedia.themoviedb.commons.InfiniteScrollListener
import ec.solmedia.themoviedb.commons.extensions.inflate
import ec.solmedia.themoviedb.domain.RequestMediaCommand
import ec.solmedia.themoviedb.model.Media
import ec.solmedia.themoviedb.model.MediaItem
import ec.solmedia.themoviedb.view.activity.MediaDetailActivity
import ec.solmedia.themoviedb.view.adapter.MediaAdapter
import kotlinx.android.synthetic.main.fragment_media.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import javax.inject.Inject


class MediaFragment : Fragment() {

    @Inject
    lateinit var request: RequestMediaCommand
    private lateinit var category: String
    private lateinit var mediaType: String
    private var media: Media? = null

    private val adapter = MediaAdapter { navigateToMediaDetail(it) }

    companion object {
        val KEY_TITLE = "title"
        val KEY_ITEMS = "items"
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

        savedInstanceState?.containsKey(KEY_ITEMS)?.let {
            Log.d("MediaFragment", "Saved instance")
            media = savedInstanceState.getParcelable(KEY_ITEMS)
            val mediaSaved = media?.mediaItems ?: emptyList()
            adapter.clearAndAddMediaItems(mediaSaved)
            activity.title = savedInstanceState.get(KEY_TITLE) as CharSequence?
        } ?: kotlin.run {
            Log.d("MediaFragment", "RequestMedia")
            requestMedia()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_TITLE, activity.title.toString())
        val adapterMediaItems = adapter.getMediaItems()
        media?.let {
            adapterMediaItems.let {
                outState.putParcelable(KEY_ITEMS, media?.copy(mediaItems = adapterMediaItems))
            }
        }
    }

    private fun requestMedia() {
        request.execute(mediaType, category, ::showError) {
            adapter.addMediaItems(it.mediaItems)
            this.media = it
        }
    }

    private fun setupInjection() {
        VimoApp.graph.plus().inject(this)
    }

    private fun setupRecyclerView() {
        rvMedia.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener(linearLayout) {
                requestMedia()
            })
        }
    }

    private fun setupAdapter() {
        if (rvMedia.adapter == null) {
            rvMedia.adapter = adapter
        }
    }

    private fun showError() {
        adapter.removeLoadingItem()
        activity.longToast(getString(R.string.msg_error_media))
    }

    private fun navigateToMediaDetail(mediaItem: MediaItem) {
        activity.startActivity<MediaDetailActivity>(
                MediaDetailActivity.EXTRA_ID to mediaItem.id,
                MediaDetailActivity.EXTRA_MEDIA_TYPE to mediaType,
                MediaDetailActivity.EXTRA_CATEGORY to category
        )
    }
}
