package ec.solmedia.themoviedb.domain

import ec.solmedia.themoviedb.api.DetailItemResponse
import ec.solmedia.themoviedb.api.GenreResponse
import ec.solmedia.themoviedb.api.TheMovieDBDataResponse
import ec.solmedia.themoviedb.api.TheMovieDBResponse
import ec.solmedia.themoviedb.db.DetailMediaItemEntity
import ec.solmedia.themoviedb.db.GenreEntity
import ec.solmedia.themoviedb.model.DetailMediaItem
import ec.solmedia.themoviedb.model.Genre
import ec.solmedia.themoviedb.model.Media
import ec.solmedia.themoviedb.model.MediaItem

class DataMapper {

    fun convertFromApiModel(response: TheMovieDBResponse): Media {
        return Media(response.page,
                response.total_results,
                response.total_pages,
                convertResultsListToDomain(response.results))
    }

    private fun convertResultsListToDomain(results: List<TheMovieDBDataResponse>): List<MediaItem> {
        return results.map {
            MediaItem(it.id,
                    it.title,
                    it.name,
                    it.overview,
                    it.first_air_date,
                    it.release_date,
                    it.original_title,
                    it.original_name,
                    it.original_language,
                    it.popularity,
                    it.vote_average,
                    it.vote_count,
                    it.poster_path ?: "",
                    it.backdrop_path ?: "")
        }
    }

    fun convertDetailItemFromApi(response: DetailItemResponse): DetailMediaItem {

        with(response) {
            return DetailMediaItem(id,
                    overview,
                    homepage,
                    name,
                    in_production,
                    original_name,
                    first_air_date,
                    last_air_date,
                    number_of_episodes,
                    number_of_seasons,
                    type,
                    title,
                    imdb_id,
                    release_date,
                    revenue,
                    original_title,
                    runtime,
                    budget,
                    tagline,
                    original_language,
                    popularity,
                    vote_average,
                    vote_count,
                    status,
                    poster_path ?: "",
                    backdrop_path ?: "",
                    convertGenresFromApi(genres))
        }
    }

    private fun convertGenresFromApi(genres: Array<GenreResponse>): List<Genre> {
        return genres.map {
            Genre(it.id, it.name)
        }
    }

    fun convertDetailMediaItemToDataBase(detailMediaItem: DetailMediaItem): DetailMediaItemEntity {
        with(detailMediaItem) {
            val genre = genres.map { convertGenreToDataBase(id, it) }
            return DetailMediaItemEntity(
                    id,
                    overview,
                    homepage,
                    name,
                    "N",
                    originalName,
                    firstAirDate,
                    lastAirDate,
                    numberOfEpisodes,
                    numberOfSeasons,
                    type,
                    title,
                    imdbId,
                    releaseDate,
                    revenue,
                    originalTitle,
                    runtime,
                    budget,
                    tagline,
                    originalLanguage,
                    popularity,
                    voteAverage,
                    voteCount,
                    status,
                    posterPath,
                    backDropPath,
                    genre)
        }
    }

    private fun convertGenreToDataBase(detailMediaItemId: Int, genre: Genre): GenreEntity {
        return GenreEntity(genre.id, genre.name, detailMediaItemId)
    }

    fun convertDataBaseToDetailMediaItem(detailMediaItemEntity: DetailMediaItemEntity): DetailMediaItem {
        with(detailMediaItemEntity) {
            val genres = genreEntityList.map { covertDataBaseToGenre(it) }
            return DetailMediaItem(_id,
                    overview,
                    homepage,
                    name,
                    inProduction == "S",
                    originalName,
                    firstAirDate,
                    lastAirDate,
                    numberOfEpisodes,
                    numberOfSeasons,
                    type,
                    title,
                    imdbId,
                    releaseDate,
                    revenue,
                    originalTitle,
                    runtime,
                    budget,
                    tagline,
                    originalLanguage,
                    popularity,
                    voteAverage,
                    voteCount,
                    status,
                    posterPath,
                    backDropPath, genres)
        }
    }

    private fun covertDataBaseToGenre(genreEntity: GenreEntity): Genre {
        return Genre(genreEntity._id, genreEntity.name)
    }
}