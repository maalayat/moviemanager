package ec.solmedia.themoviedb.api

class TheMovieDBResponse(val page: Int, val results: List<TheMovieDBDataResponse>)

class TheMovieDBDataResponse(val id: String,
                             val title: String,
                             val overview: String,
                             val vote_average: Float,
                             val vote_count: Int,
                             val poster_path: String?,
                             val backdrop_path: String?)
