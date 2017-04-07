package ec.solmedia.moviemanager.view.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ec.solmedia.moviemanager.R
import ec.solmedia.moviemanager.commons.extensions.inflate
import ec.solmedia.moviemanager.model.Movie
import ec.solmedia.moviemanager.view.activity.MovieDetailActivity
import ec.solmedia.moviemanager.view.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_upcoming.*


/**
 * A simple [Fragment] subclass.
 */
class UpcomingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_upcoming)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()
        setupAdapter()
    }

    private fun setupRecyclerView() {
        rvUpMovies.setHasFixedSize(true)
        rvUpMovies.layoutManager = LinearLayoutManager(context)
    }

    private fun setupAdapter() {
        if (rvUpMovies.adapter == null) {
            rvUpMovies.adapter = MovieAdapter(initDummyData()) { movie ->
                navigateToMovieDetail(movie)
            }
        }
    }

    private fun initDummyData(): List<Movie> {
        val moviesUp = mutableListOf<Movie>()

        moviesUp.add(Movie("277834", "Rings", "Julia (Matilda Lutz) becomes worried about her boyfriend, Holt (Alex Roe) when he explores the dark urban legend of a mysterious videotape said to kill the watcher seven days after viewing. She sacrifices herself to save her boyfriend and in doing so makes a horrifying discovery: there is a \\\"movie within the movie\\\" that no one has ever seen before.", 0f, 0, "https://image.tmdb.org/t/p/w342/uwNNLJULvTQfgc3PBZAY92EOJQO.jpg", "https://image.tmdb.org/t/p/w780/91WPDonXsxRzi7AcfedKM3p3NFU.jpg"))
        moviesUp.add(Movie("121856", "The Great Wall", "The story of an elite force making a last stand for humanity on the world’s most iconic structure.", 0f, 0, "https://image.tmdb.org/t/p/w342/hm0Z5tpRlSzPO97U5e2Q32Y0Xrb.jpg", "https://image.tmdb.org/t/p/w780/yESCAoZkaxZ2AMiHojl9jYYd9zD.jpg"))
        moviesUp.add(Movie("330459", "Patriots Day", "An account of Boston Police Commissioner Ed Davis's actions in the events leading up to the 2013 Boston Marathon bombing and the aftermath, which includes the city-wide manhunt to find the terrorists behind it.", 0f, 0, "https://image.tmdb.org/t/p/w342/gd4SDPZIdVRAxUolQ9ZCNXTkQUq.jpg", "https://image.tmdb.org/t/p/w780/tiBL4PeaCPKGBz3qO4dJP2KzKop.jpg"))
        moviesUp.add(Movie("283366", "T2 Trainspotting", "First there was an opportunity......then there was a betrayal. Twenty years have gone by. Much has changed but just as much remains the same. ", 0f, 0, "https://image.tmdb.org/t/p/w342/A84pxL5z86KbV9u8GAK2mVOoXK.jpg", "https://image.tmdb.org/t/p/w780/dxJynNxoG6jRttYPoXOGx7OWJvq.jpg"))
        return moviesUp
    }

    private fun navigateToMovieDetail(movie: Movie) {
        val intent: Intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie)
        startActivity(intent)
    }
}
