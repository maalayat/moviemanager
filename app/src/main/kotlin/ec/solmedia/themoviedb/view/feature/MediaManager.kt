package ec.solmedia.themoviedb.view.feature

import ec.solmedia.themoviedb.api.RestAPI
import ec.solmedia.themoviedb.model.Media
import ec.solmedia.themoviedb.model.MediaItem
import rx.Observable

class MediaManager(private val api: RestAPI = RestAPI()) {

    fun get(type: String, page: Int): Observable<Media> {
        return Observable.create {
            subscriber ->
            val callResponse = api.get(type, page + 1)
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