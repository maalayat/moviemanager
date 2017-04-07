package ec.solmedia.moviemanager.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ec.solmedia.moviemanager.R
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
