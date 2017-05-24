package ec.solmedia.themoviedb.view.fragment

import android.content.Intent
import android.support.v4.app.Fragment
import ec.solmedia.themoviedb.model.Media
import ec.solmedia.themoviedb.model.MediaItem
import ec.solmedia.themoviedb.view.activity.MediaDetailActivity
import rx.subscriptions.CompositeSubscription

open class RxBaseFragment : Fragment() {

    protected var subscriptions = CompositeSubscription()
    protected var media: Media? = null

    companion object {
        val KEY_MEDIA = "media"
    }

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeSubscription()
    }

    override fun onPause() {
        super.onPause()
        subscriptions.clear()
    }

    protected fun navigateToMediaDetail(mediaItem: MediaItem) {
        val intent: Intent = Intent(context, MediaDetailActivity::class.java)
        intent.putExtra(MediaDetailActivity.EXTRA_MEDIA_ITEM, mediaItem)
        startActivity(intent)
    }
}