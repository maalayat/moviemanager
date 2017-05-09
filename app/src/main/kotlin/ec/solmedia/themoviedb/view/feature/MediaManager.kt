package ec.solmedia.themoviedb.view.feature

import ec.solmedia.themoviedb.api.RestAPI
import ec.solmedia.themoviedb.model.MediaItem
import rx.Observable

class MediaManager(private val api: RestAPI = RestAPI()) {

    fun get(type: String, page: Int = 1): Observable<List<MediaItem>> {
        return Observable.create {
            subscriber ->
            val callResponse = api.get(type, page)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val mediaItems = response.body().results.map {
                    MediaItem(it.id, it.title, it.overview, it.vote_average,
                            it.vote_count, it.poster_path, it.backdrop_path)

                }
                subscriber.onNext(mediaItems)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }

    }
}