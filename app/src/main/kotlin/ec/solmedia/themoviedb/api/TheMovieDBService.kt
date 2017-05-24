package ec.solmedia.themoviedb.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBService {

    @GET("movie/{type}")
    fun get(@Path("type") type: String,
            @Query("api_key") apiKey: String,
            @Query("page") page: Int,
            @Query("language") language: String = "en-US"): Call<TheMovieDBResponse>

}