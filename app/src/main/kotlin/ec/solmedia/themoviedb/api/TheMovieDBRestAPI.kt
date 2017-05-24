package ec.solmedia.themoviedb.api

import ec.solmedia.themoviedb.TheMovieDBApp
import retrofit2.Call
import javax.inject.Inject

class TheMovieDBRestAPI @Inject constructor(private val service: TheMovieDBService) : TheMovieDBAPI {

    override fun get(type: String, page: Int): Call<TheMovieDBResponse> {
        return service.get(type, TheMovieDBApp.API_KEY, page)
    }

}