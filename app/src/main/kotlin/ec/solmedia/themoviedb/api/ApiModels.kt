package ec.solmedia.themoviedb.api

class TheMovieDBResponse(
        val page: Int,
        val total_results: Int,
        val total_pages: Int,
        val results: List<TheMovieDBDataResponse>)

class TheMovieDBDataResponse(
        val id: Int,
        val title: String?,
        val name: String?,
        val overview: String,
        val first_air_date: String?,
        val release_date: String?,
        val original_title: String?,
        val original_name: String?,
        val original_language: String,
        val popularity: Float,
        val vote_average: Float,
        val vote_count: Int,
        val poster_path: String?,
        val backdrop_path: String?)
