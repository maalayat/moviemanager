package ec.solmedia.themoviedb.api

class TheMovieDBResponse(val page: Int,
                         val total_results: Int,
                         val total_pages: Int,
                         val results: List<TheMovieDBDataResponse>)

class TheMovieDBDataResponse(val id: String,
                             val title: String?,
                             val name: String?, //tv
                             val overview: String,
                             val first_air_date: String?, //tv
                             val release_date: String?, //val genre_ids: Array<Int>,
                             val original_title: String?,
                             val original_name: String?, //tv
                             val original_language: String, //val origin_country: String?,//tv
                             val popularity: Float,
                             val vote_average: Float,
                             val vote_count: Int,
                             val poster_path: String?,
                             val backdrop_path: String?)
