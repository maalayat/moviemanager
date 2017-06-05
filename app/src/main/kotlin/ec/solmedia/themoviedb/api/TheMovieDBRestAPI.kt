package ec.solmedia.themoviedb.api

import ec.solmedia.themoviedb.BuildConfig
import retrofit2.Call
import javax.inject.Inject

class TheMovieDBRestAPI @Inject constructor(private val service: TheMovieDBService) : TheMovieDBAPI {

    override fun get(type: String, page: Int): Call<TheMovieDBResponse> {
        return service.get(type, BuildConfig.TMDB_API_KEY, page)
    }

}