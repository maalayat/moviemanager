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
import kotlinx.android.synthetic.main.fragment_detail_tv.*


class FragmentDetailTv : Fragment() {

    private lateinit var detailMediaItem: DetailMediaItem

    fun setDetailMediaItem(_detailMediaItem: DetailMediaItem) {
        detailMediaItem = _detailMediaItem
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            container?.inflate(R.layout.fragment_detail_tv)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupView()
    }

    private fun setupView() {
        ivDetTvPoster.loadImg(
                detailMediaItem.posterPath,
                VimoApp.PATH_POSTER,
                resources.getDimensionPixelSize(R.dimen.poster_width),
                resources.getDimensionPixelSize(R.dimen.poster_height))
        tvDetTvVoteCount.text = detailMediaItem.voteCount.toString()
        tvDetTvStatus.text = detailMediaItem.status
        tvDetFirstAirDate.text = detailMediaItem.firstAirDate
        tvDetLastAirDate.text = detailMediaItem.lastAirDate
        tvDetNumberOfEpisodes.text = detailMediaItem.numberOfEpisodes.toString()
        tvDetNumberOfSeasons.text = detailMediaItem.numberOfSeasons.toString()
    }
}