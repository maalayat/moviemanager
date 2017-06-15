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

    fun get(mediaType: String, category: String, pageValue: Int): Observable<Media> {
        return Observable.create {
            subscriber ->
            val callResponse = api.get(
                    mediaType,
                    category,
                    pageValue + 1,
                    sPref.getString(TheMovieDBApp.LOCALE_KEY, "en-US")
            )
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val mediaResponse = response.body()
                val mediaItems = mediaResponse.results.map {
                    MediaItem(
                            it.id,
                            it.title,
                            it.name,
                            it.overview,
                            it.first_air_date,
                            it.release_date,
                            it.original_title,
                            it.original_name,
                            it.original_language,
                            it.popularity,
                            it.vote_average,
                            it.vote_count,
                            it.poster_path ?: "",
                            it.backdrop_path ?: "")
                }
                with(mediaResponse) {
                    val media = Media(page, total_results, total_pages, mediaItems)
                    subscriber.onNext(media)
                }
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }

    }
}