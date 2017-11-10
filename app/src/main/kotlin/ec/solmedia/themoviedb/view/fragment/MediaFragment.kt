package ec.solmedia.themoviedb.view.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ec.solmedia.themoviedb.R
import ec.solmedia.themoviedb.VimoApp
import ec.solmedia.themoviedb.commons.InfiniteScrollListener
import ec.solmedia.themoviedb.commons.extensions.inflate
import ec.solmedia.themoviedb.domain.RequestMediaCommand
import ec.solmedia.themoviedb.model.MediaItem
import ec.solmedia.themoviedb.view.activity.MediaDetailActivity
import ec.solmedia.themoviedb.view.adapter.MediaAdapter
import kotlinx.android.synthetic.main.fragment_media.*
import javax.inject.Inject


class MediaFragment : Fragment() {

    @Inject lateinit var request: RequestMediaCommand
    private lateinit var category: String
    private lateinit var mediaType: String

    private val adapter = MediaAdapter { navigateToMediaDetail(it) }

    companion object {
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
        requestMedia()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY_TITLE, activity.title.toString())

        super.onSaveInstanceState(outState)
    }

    private fun requestMedia() {
        request.execute(mediaType, category) {
            adapter.addMediaItems(it.mediaItems)
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

    private fun navigateToMediaDetail(mediaItem: MediaItem) {
        val intent = Intent(context, MediaDetailActivity::class.java)
        intent.putExtra(MediaDetailActivity.EXTRA_ID, mediaItem.id)
        intent.putExtra(MediaDetailActivity.EXTRA_MEDIA_TYPE, mediaType)
        intent.putExtra(MediaDetailActivity.EXTRA_CATEGORY, category)

        startActivity(intent)
    }
}
