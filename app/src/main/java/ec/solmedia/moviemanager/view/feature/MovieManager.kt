package ec.solmedia.moviemanager.view.feature

import ec.solmedia.moviemanager.model.Movie
import ec.solmedia.moviemanager.api.RestAPI
import rx.Observable

class MovieManager(private val api: RestAPI = RestAPI()) {

    fun get(type: String): Observable<List<Movie>> {
        return Observable.create {
            subscriber ->
            val callResponse = api.get(type)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val nowMovies = response.body().results.map {
                    Movie(it.id, it.title, it.overview, it.vote_average,
                            it.vote_count, it.poster_path, it.backdrop_path)
                }
                subscriber.onNext(nowMovies)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }

    }
}