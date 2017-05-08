package ec.solmedia.moviemanager.view.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDatabaseApi {

    @GET("movie/{type}")
    fun get(@Path("type") type: String, @Query("api_key") apiKey: String): Call<MovieDBResponse>

}