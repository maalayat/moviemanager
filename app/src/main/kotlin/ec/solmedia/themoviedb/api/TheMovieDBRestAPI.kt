package ec.solmedia.themoviedb.api

import ec.solmedia.themoviedb.BuildConfig
import retrofit2.Call
import javax.inject.Inject

class TheMovieDBRestAPI @Inject constructor(private val service: TheMovieDBService) : TheMovieDBAPI {

    override fun get(media: String, category: String, page: Int, locale: String): Call<TheMovieDBResponse> {
        return service.get(media, category, BuildConfig.TMDB_API_KEY, page, locale)
    }

    override fun detail(mediaType: String, mediaId: Int, locale: String): Call<TheMovieDBDataResponse> {
        return service.detail(mediaType, mediaId, BuildConfig.TMDB_API_KEY, locale)
    }

}