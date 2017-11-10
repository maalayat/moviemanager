package ec.solmedia.themoviedb.api

class DetailItemResponse(
        val id: Int,
        val overview: String,
        val homepage: String,//null
        val name: String?, //tv
        val in_production: Boolean?, //tv
        val original_name: String?, //tv
        val first_air_date: String?, //tv
        val last_air_date: String?, //tv
        val number_of_episodes: Int?, //tv
        val number_of_seasons: Int?, //tv
        val type: String?, //tv
        val title: String?, //movie
        val imdb_id: String?, //movie null
        val release_date: String?, //movie
        val revenue: Int?, //movie
        val original_title: String?,
        val runtime: Int?, //movie null
        val budget: Int?, //movie
        val tagline: String?, //movie null
        val original_language: String,
        val popularity: Float,
        val vote_average: Float,
        val vote_count: Int,
        val status: String,
        val poster_path: String?, //null
        val backdrop_path: String?, //null
        val genres: Array<GenreResponse>)

class GenreResponse(val id: Int, val name: String)
