package ec.solmedia.themoviedb.view.fragment

import android.support.v4.app.Fragment
import ec.solmedia.themoviedb.model.Media
import rx.subscriptions.CompositeSubscription

open class RxBaseFragment : Fragment() {

    protected var subscriptions = CompositeSubscription()
    protected var media: Media? = null

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeSubscription()
    }

    override fun onPause() {
        super.onPause()
        subscriptions.clear()
    }
}