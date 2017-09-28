package ec.solmedia.themoviedb.domain

import android.content.SharedPreferences
import android.util.Log
import ec.solmedia.themoviedb.TheMovieDBApp
import ec.solmedia.themoviedb.api.TheMovieDBAPI
import ec.solmedia.themoviedb.model.Media
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject

class RequestMediaCommand @Inject constructor(
        private val api: TheMovieDBAPI,
        private val sPref: SharedPreferences) : Command<Media> {

    private var media = Media()

    override fun execute(mediaType: String, category: String, func: (Media) -> Unit) {
        Log.d("MediaFragment", "actualPage: ${media.page} totalPage: ${media.totalPages}")
        doAsync {
            if (media.page < media.totalPages) {
                val callResponse = api.get(mediaType, category, media.page + 1,
                        sPref.getString(TheMovieDBApp.LOCALE_KEY, "en-US")
                )
                val response = callResponse.execute()

                uiThread {
                    if (response.isSuccessful) {
                        val mediaResponse = response.body()
                        val mediaConverted = MediaDataMapper().convertFromApiModel(mediaResponse)
                        media = mediaConverted
                        func(mediaConverted)
                    }
                }
            }
        }
    }
}