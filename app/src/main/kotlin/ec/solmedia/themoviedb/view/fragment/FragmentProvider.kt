package ec.solmedia.themoviedb.view.fragment

import android.os.Bundle
import ec.solmedia.themoviedb.view.fragment.MediaFragment

object FragmentProvider {
    val EXTRA_CATEGORY = "FragmentProvider:category"
    val EXTRA_MEDIA_TYPE = "FragmentProvider:mediaType"

    fun newInstance(mediaType: String, category: String): MediaFragment {
        val fragment = MediaFragment()
        val args = Bundle()
        args.putString(EXTRA_CATEGORY, category)
        args.putString(EXTRA_MEDIA_TYPE, mediaType)
        fragment.arguments = args
        return fragment
    }
}