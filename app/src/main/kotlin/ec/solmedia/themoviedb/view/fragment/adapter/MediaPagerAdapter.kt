package ec.solmedia.themoviedb.view.fragment.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import ec.solmedia.themoviedb.view.fragment.FragmentProvider
import ec.solmedia.themoviedb.view.fragment.SmartFragmentStatePagerAdapter

class MediaPagerAdapter(
        fm: FragmentManager,
        val mediaType: String,
        val categories: List<String>,
        val titles: List<String>) : SmartFragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int) =
            FragmentProvider.newInstance(mediaType, categories[position])

    override fun getPageTitle(position: Int): CharSequence = titles[position]

    override fun getCount(): Int = categories.size
}