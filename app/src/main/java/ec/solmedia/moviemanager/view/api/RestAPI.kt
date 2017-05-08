package ec.solmedia.moviemanager.view.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RestAPI {

    private val URL: String = "https://api.themoviedb.org/3/"
    private val API_KEY: String = ""

    private val movieDatabaseApi: MovieDatabaseApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        movieDatabaseApi = retrofit.create(MovieDatabaseApi::class.java)
    }

    fun get(type: String): Call<MovieDBResponse> = movieDatabaseApi.get(type, API_KEY)
}