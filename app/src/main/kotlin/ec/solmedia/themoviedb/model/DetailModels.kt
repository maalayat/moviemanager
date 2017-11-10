package ec.solmedia.themoviedb.model

data class DetailMediaItem(
        val id: Int,
        val overview: String,
        val homepage: String,
        val name: String?,
        val inProduction: Boolean?,
        val originalName: String?,
        val firstAirDate: String?,
        val lastAirDate: String?,
        val numberOfEpisodes: Int?,
        val numberOfSeasons: Int?,
        val type: String?,
        val title: String?,
        val imdbId: String?,
        val releaseDate: String?,
        val revenue: Int?,
        val originalTitle: String?,
        val runtime: Int?,
        val budget: Int?,
        val tagline: String?,
        val originalLanguage: String,
        val popularity: Float,
        val voteAverage: Float,
        val voteCount: Int,
        val status: String,
        val posterPath: String,
        val backDropPath: String,
        val genres: List<Genre>) {

    @Transient
    var isFavorite: Boolean = false
}

data class Genre(val id: Int, val name: String)
