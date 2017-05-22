package ec.solmedia.themoviedb.api

import retrofit2.Call

interface MediaAPI {
    fun get(type: String, page: Int): Call<MediaDBResponse>
}