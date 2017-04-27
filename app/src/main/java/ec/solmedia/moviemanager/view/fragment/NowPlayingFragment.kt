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
import ec.solmedia.moviemanager.view.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_now_playing.*


class NowPlayingFragment : Fragment() {

    /*private val rvListMovies by lazy {
        rvMovies
    }*/

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_now_playing)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()
        initAdapter()

        if(savedInstanceState == null) {
            val movies = initDummyData();
            (rvMovies.adapter as MoviesAdapter).addMovies(movies)
        }
    }

    private fun setupRecyclerView() {
        rvMovies.setHasFixedSize(true)
        rvMovies.layoutManager = LinearLayoutManager(context)
    }

    private fun initAdapter() {
        if (rvMovies.adapter == null) {
            rvMovies.adapter = MoviesAdapter() {
                navigateToMovieDetail(it)
            }
        }
    }

    private fun initDummyData(): List<Movie> {
        val movies = mutableListOf<Movie>()
        movies.add(Movie("277834", "Moana", "In Ancient Polynesia, when a terrible curse incurred by Maui reaches an impetuous Chieftain's daughter's island, she answers the Ocean's call to seek out the demigod to set things right.", 6.5f, 854, "https://image.tmdb.org/t/p/w342/z4x0Bp48ar3Mda8KiPD1vwSY3D8.jpg", "https://image.tmdb.org/t/p/w780/1qGzqGUd1pa05aqYXGSbLkiBlLB.jpg"))
        movies.add(Movie("121856", "Passengers", "A spacecraft traveling to a distant colony planet and transporting thousands of people has a malfunction in its sleep chambers. As a result, two passengers are awakened 90 years early.", 6.2f, 745, "https://image.tmdb.org/t/p/w342/5gJkVIVU7FDp7AfRAbPSvvdbre2.jpg", "https://image.tmdb.org/t/p/w780/5EW4TR3fWEqpKsWysNcBMtz9Sgp.jpg"))
        movies.add(Movie("330459", "Assassin's Creed", "Lynch discovers he is a descendant of the secret Assassins society through unlocked genetic memories that allow him to relive the adventures of his ancestor, Aguilar, in 15th Century Spain. After gaining incredible knowledge and skills he’s poised to take on the oppressive Knights Templar in the present day.", 5.3f, 691, "https://image.tmdb.org/t/p/w342/tIKFBxBZhSXpIITiiB5Ws8VGXjt.jpg", "https://image.tmdb.org/t/p/w780/5EW4TR3fWEqpKsWysNcBMtz9Sgp.jpg"))
        movies.add(Movie("283366", "Rogue One: A Star Wars Story", "A rogue band of resistance fighters unite for a mission to steal the Death Star plans and bring a new hope to the galaxy.", 7.2f, 1802, "https://image.tmdb.org/t/p/w342/qjiskwlV1qQzRCjpV0cL9pEMF9a.jpg", "https://image.tmdb.org/t/p/w780/tZjVVIYXACV4IIIhXeIM59ytqwS.jpg"))
        movies.add(Movie("313369", "La La Land", "Mia, an aspiring actress, serves lattes to movie stars in between auditions and Sebastian, a jazz musician, scrapes by playing cocktail party gigs in dingy bars, but as success mounts they are faced with decisions that begin to fray the fragile fabric of their love affair, and the dreams they worked so hard to maintain in each other threaten to rip them apart.", 8f, 396, "https://image.tmdb.org/t/p/w342/ylXCdC106IKiarftHkcacasaAcb.jpg", "https://image.tmdb.org/t/p/w780/nadTlnTE6DdgmYsN4iWc2a2wiaI.jpg"))

        return movies
    }

    private fun navigateToMovieDetail(movie: Movie) {
        val intent: Intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie)
        startActivity(intent)
    }
}
