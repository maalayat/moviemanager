package ec.solmedia.themoviedb.view.feature

import android.content.SharedPreferences
import ec.solmedia.themoviedb.TheMovieDBApp
import ec.solmedia.themoviedb.api.TheMovieDBAPI
import ec.solmedia.themoviedb.model.Media
import ec.solmedia.themoviedb.model.MediaItem
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaManager @Inject constructor(
        private val api: TheMovieDBAPI,
        private val sPref: SharedPreferences) {

    fun get(type: String, page: Int): Observable<Media> {
        return Observable.create {
            subscriber ->
            val callResponse = api.get(
                    type,
                    page + 1,
                    sPref.getString(TheMovieDBApp.LOCALE_KEY, "en-US")
            )
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val mediaResponse = response.body()
                val mediaItems = mediaResponse.results.map {
                    MediaItem(it.id, it.title, it.overview, it.vote_average,
                            it.vote_count, it.poster_path, it.backdrop_path)
                }
                val media = Media(mediaResponse.page, mediaItems)
                subscriber.onNext(media)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }

    }
}