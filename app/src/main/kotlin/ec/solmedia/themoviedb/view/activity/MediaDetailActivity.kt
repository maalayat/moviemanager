package ec.solmedia.themoviedb.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import ec.solmedia.themoviedb.R
import ec.solmedia.themoviedb.commons.extensions.consume
import ec.solmedia.themoviedb.commons.extensions.loadImg
import ec.solmedia.themoviedb.commons.extensions.snack
import ec.solmedia.themoviedb.model.MediaItem
import kotlinx.android.synthetic.main.activity_media_detail.*
import kotlinx.android.synthetic.main.content_media_detail.*

class MediaDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_MEDIA_ITEM = "MediaDetail"
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
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupView() {
        val mediaItem: MediaItem = intent.extras.getParcelable(EXTRA_MEDIA_ITEM)
        title = mediaItem.title
        tvOverView.text = mediaItem.overView
        ivMediaBackdrop.loadImg(mediaItem.backDropPath)
    }
}
