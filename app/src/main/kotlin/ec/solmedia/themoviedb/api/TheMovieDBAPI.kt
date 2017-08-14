package ec.solmedia.themoviedb.api

import retrofit2.Call

interface TheMovieDBAPI {
    fun get(media: String, category: String, page: Int, locale: String): Call<TheMovieDBResponse>
    fun detail(mediaType: String, mediaId: Int, locale: String): Call<TheMovieDBDataResponse>
}