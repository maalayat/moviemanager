package ec.solmedia.themoviedb.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ec.solmedia.themoviedb.R
import ec.solmedia.themoviedb.VimoApp
import ec.solmedia.themoviedb.commons.extensions.inflate
import ec.solmedia.themoviedb.commons.extensions.loadImg
import ec.solmedia.themoviedb.model.DetailMediaItem
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import java.text.NumberFormat


class FragmentDetailMovie : Fragment() {

    private lateinit var detailMediaItem: DetailMediaItem

    fun setDetailMediaItem(_detailMediaItem: DetailMediaItem) {
        detailMediaItem = _detailMediaItem
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            container?.inflate(R.layout.fragment_detail_movie)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupView()
    }

    private fun setupView() {
        ivDetMoviePoster.loadImg(
                detailMediaItem.posterPath,
                VimoApp.PATH_POSTER,
                resources.getDimensionPixelSize(R.dimen.poster_width),
                resources.getDimensionPixelSize(R.dimen.poster_height))
        tvDetMovieStatus.text = detailMediaItem.status
        tvDetReleaseDate.text = detailMediaItem.releaseDate
        tvDetImdb.text = detailMediaItem.imdbId
        tvDetRevenue.text = money(detailMediaItem.revenue?.toLong())
        tvDetRuntime.text = detailMediaItem.runtime.toString()
        tvDetBudget.text = money(detailMediaItem.budget?.toLong())
    }

    private fun money(value: Long?): String {
        val formatter = NumberFormat.getCurrencyInstance()
        return formatter.format(value)

    }
}
