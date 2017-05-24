package ec.solmedia.themoviedb.api

import retrofit2.Call

interface TheMovieDBAPI {
    fun get(type: String, page: Int): Call<TheMovieDBResponse>
}