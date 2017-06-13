package ec.solmedia.themoviedb.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBService {

    @GET("{media}/{category}")
    fun get(@Path("media") media: String,
            @Path("category") category: String,
            @Query("api_key") apiKey: String,
            @Query("page") page: Int,
            @Query("language") language: String = "en-US"): Call<TheMovieDBResponse>

}