package ec.solmedia.themoviedb.view.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import ec.solmedia.themoviedb.R
import ec.solmedia.themoviedb.TheMovieDBApp
import ec.solmedia.themoviedb.commons.extensions.consume
import ec.solmedia.themoviedb.commons.extensions.loadImg
import ec.solmedia.themoviedb.commons.extensions.snack
import kotlinx.android.synthetic.main.activity_media_detail.*
import kotlinx.android.synthetic.main.content_media_detail.*

class MediaDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_TITLE = "MediaDetailActivity:title"
        val EXTRA_OVERVIEW = "MediaDetailActivity:overview"
        val EXTRA_BACK_DROP = "MediaDetailActivity:backdrop"
        val EXTRA_POSTER = "MediaDetailActivity:poster"
        val EXTRA_VOTE_AVERAGE = "MediaDetailActivity:voteAverage"
        val EXTRA_VOTE_COUNT = "MediaDetailActivity:voteCount"
        val EXTRA_RELEASE_DATE = "MediaDetailActivity:realeaseDate"
        val EXTRA_ORIGINAL_NAME = "MediaDetailActivity:originalName"
        val EXTRA_POPULARITY = "MediaDetailActivity:popularity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_detail)
        setSupportActionBar(toolbarDetail)

        setupListener()
        setupActionBar()
        setupView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> consume { onBackPressed() }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setupListener() {
        fab.setOnClickListener { view ->
            view.snack("Media saved as favorite") {}
        }
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setupView() = with(intent.extras) {
        title = ""
        tvDetTitle.text = getString(EXTRA_TITLE)
        tvDetOverview.text = getString(EXTRA_OVERVIEW)
        ivDetBackdrop.loadImg(
                getString(EXTRA_BACK_DROP),
                TheMovieDBApp.PATH_BACKDROP,
                resources.getDimensionPixelSize(R.dimen.backDrop_width),
                resources.getDimensionPixelSize(R.dimen.backDrop_height))
        ivDetPoster.loadImg(
                getString(EXTRA_POSTER),
                TheMovieDBApp.PATH_POSTER,
                resources.getDimensionPixelSize(R.dimen.poster_width),
                resources.getDimensionPixelSize(R.dimen.poster_height))
        tvDetVoteCount.text = get(EXTRA_VOTE_COUNT).toString()
        tvDetVoteAverage.text = get(EXTRA_VOTE_AVERAGE).toString()
        tvDetReleaseDate.text = get(EXTRA_RELEASE_DATE).toString()
        tvDetOriginalName.text = getString(EXTRA_ORIGINAL_NAME)
        tvDetPopularity.text = get(EXTRA_POPULARITY).toString()
    }
}
