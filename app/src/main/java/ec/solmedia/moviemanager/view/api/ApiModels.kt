package ec.solmedia.moviemanager.view.api

class MovieDBResponse(val page: Int, val results: List<MovieDBDataResponse>)

class MovieDBDataResponse(val id: String,
                          val title: String,
                          val overview: String,
                          val vote_average: Float,
                          val vote_count: Int,
                          val poster_path: String,
                          val backdrop_path: String)
