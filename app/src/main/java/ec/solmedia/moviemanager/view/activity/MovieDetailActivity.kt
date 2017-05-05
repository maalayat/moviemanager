package ec.solmedia.moviemanager.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import ec.solmedia.moviemanager.R
import ec.solmedia.moviemanager.commons.extensions.consume
import ec.solmedia.moviemanager.commons.extensions.loadImg
import ec.solmedia.moviemanager.commons.extensions.snack
import ec.solmedia.moviemanager.model.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.content_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_MOVIE = "MovieDetail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
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
            view.snack("Movie saved as favorite") {}
        }
    }

    private fun setupActionBar() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupView() {
        val movie: Movie = intent.extras.getParcelable(EXTRA_MOVIE)
        title = movie.title
        tvOverView.text = movie.overView
        ivMovieBackdrop.loadImg(movie.backDropPath)
    }
}
