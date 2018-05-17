package ec.solmedia.themoviedb.view.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import ec.solmedia.themoviedb.R
import ec.solmedia.themoviedb.VimoApp
import ec.solmedia.themoviedb.commons.extensions.consume
import ec.solmedia.themoviedb.commons.extensions.inTransaction
import ec.solmedia.themoviedb.commons.extensions.loadImg
import ec.solmedia.themoviedb.commons.extensions.snack
import ec.solmedia.themoviedb.domain.RequestDetailMediaCommand
import ec.solmedia.themoviedb.model.DetailMediaItem
import ec.solmedia.themoviedb.view.fragment.FragmentDetailMovie
import ec.solmedia.themoviedb.view.fragment.FragmentDetailTv
import kotlinx.android.synthetic.main.activity_media_detail.*
import kotlinx.android.synthetic.main.content_media_detail.*
import javax.inject.Inject

class MediaDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var request: RequestDetailMediaCommand
    private var detailMediaItem: DetailMediaItem? = null

    companion object {
        val EXTRA_ID = "MediaDetailActivity:id"
        val EXTRA_MEDIA_TYPE = "MediaDetailActivity:mediaType"
        val EXTRA_CATEGORY = "MediaDetailActivity:category"
        val EXTRA_DETAIL_MEDIA_ITEM = "MediaDetailActivity:detailMediaItem"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_detail)
        setSupportActionBar(toolbarDetail)

        setupInjection()
        setupActionBar()
        setupView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> consume { onBackPressed() }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 2) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    private fun setupListener() {
        detailMediaItem?.let { detail ->
            fab.setOnClickListener {
                if (detail.isFavorite) {
                    request.delete(detail.id)
                    it.snack(getString(R.string.msg_detail_activity_un_save_favorite)) {}
                } else {
                    request.save(detail)
                    it.snack(getString(R.string.msg_detail_activity_save_favorite)) {}
                }
                setupFab()
            }
        }

    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setupView() {
        val mediaType = intent.extras.getString(EXTRA_MEDIA_TYPE)
        val mediaId = intent.extras.getInt(EXTRA_ID)
        request.execute(mediaType, mediaId) {
            it?.let {
                detailMediaItem = it
                tvDetTitle.text = it.title ?: it.name
                tvDetGender.text = it.genres.joinToString { it.name }
                tvDetOverview.text = it.overview
                tvDetVoteAverage.text = it.voteAverage.toString()
                ivDetBackdrop.loadImg(
                        it.backDropPath,
                        VimoApp.PATH_BACKDROP,
                        resources.getDimensionPixelSize(R.dimen.backDrop_width),
                        resources.getDimensionPixelSize(R.dimen.backDrop_height))

                if (mediaType == "tv") setupTvView() else setupMovieView()
                setupFab()
                setupListener()
            }
        }

    }

    private fun setupTvView() {
        val fragment = FragmentDetailTv()
        val bundle = Bundle()
        bundle.putParcelable(MediaDetailActivity.EXTRA_DETAIL_MEDIA_ITEM, detailMediaItem)
        fragment.arguments = bundle

        supportFragmentManager.inTransaction {
            add(R.id.flMediaDetail, fragment)
        }
    }

    private fun setupMovieView() {
        val fragment = FragmentDetailMovie()
        val bundle = Bundle()
        bundle.putParcelable(MediaDetailActivity.EXTRA_DETAIL_MEDIA_ITEM, detailMediaItem)
        fragment.arguments = bundle

        supportFragmentManager.inTransaction {
            add(R.id.flMediaDetail, fragment)
        }
    }

    private fun setupFab() {
        detailMediaItem?.let { detail ->
            fab.backgroundTintList = if (request.isFavorite(detail.id)) {
                detail.isFavorite = true
                resources.getColorStateList(R.color.colorPrimaryDark)

            } else {
                detail.isFavorite = false
                resources.getColorStateList(R.color.colorAccent)
            }
        }

    }

    private fun setupInjection() {
        VimoApp.graph.plusDetail().inject(this)
    }
}
