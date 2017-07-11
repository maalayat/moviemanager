package ec.solmedia.themoviedb.domain

import android.content.SharedPreferences
import ec.solmedia.themoviedb.TheMovieDBApp
import ec.solmedia.themoviedb.api.TheMovieDBAPI
import ec.solmedia.themoviedb.model.Media
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestMediaCommand @Inject constructor(
        private val api: TheMovieDBAPI,
        private val sPref: SharedPreferences) : Command<Media> {

    override fun execute(mediaType: String, category: String, pageValue: Int): Media? {
        val callResponse = api.get(
                mediaType,
                category,
                pageValue + 1,
                sPref.getString(TheMovieDBApp.LOCALE_KEY, "en-US")
        )
        val response = callResponse.execute()

        if (response.isSuccessful) {
            val mediaResponse = response.body()
            return MediaDataMapper().convertFromApiModel(mediaResponse)
        }

        //TODO fix
        return null
    }
}