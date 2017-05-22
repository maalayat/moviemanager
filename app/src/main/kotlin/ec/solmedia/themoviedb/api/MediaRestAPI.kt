package ec.solmedia.themoviedb.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MediaRestAPI : MediaAPI {


    private val URL: String = "https://api.themoviedb.org/3/"
    private val API_KEY: String = ""

    private val mediaDatabaseApi: MediaDatabaseApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        mediaDatabaseApi = retrofit.create(MediaDatabaseApi::class.java)
    }

    override fun get(type: String, page: Int): Call<MediaDBResponse> = mediaDatabaseApi.get(type, API_KEY, page)

}