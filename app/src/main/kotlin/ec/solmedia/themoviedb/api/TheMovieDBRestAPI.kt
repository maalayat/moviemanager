package ec.solmedia.themoviedb.api

import ec.solmedia.themoviedb.BuildConfig
import retrofit2.Call
import javax.inject.Inject

class TheMovieDBRestAPI @Inject constructor(private val service: TheMovieDBService) : TheMovieDBAPI {

    override fun get(media: String, category: String, page: Int, locale: String): Call<TheMovieDBResponse> {
        return service.get(media, category, BuildConfig.TMDB_API_KEY, page, locale)
    }

}