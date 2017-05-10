package ec.solmedia.themoviedb.api

class MediaDBResponse(val page: Int, val results: List<MediaDBDataResponse>)

class MediaDBDataResponse(val id: String,
                          val title: String,
                          val overview: String,
                          val vote_average: Float,
                          val vote_count: Int,
                          val poster_path: String?,
                          val backdrop_path: String?)
