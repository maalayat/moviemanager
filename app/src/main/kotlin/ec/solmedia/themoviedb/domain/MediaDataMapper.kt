package ec.solmedia.themoviedb.domain

import ec.solmedia.themoviedb.api.TheMovieDBDataResponse
import ec.solmedia.themoviedb.api.TheMovieDBResponse
import ec.solmedia.themoviedb.model.Media
import ec.solmedia.themoviedb.model.MediaItem

class MediaDataMapper {

    fun convertFromApiModel(response: TheMovieDBResponse): Media {
        return Media(response.page, response.total_results, response.total_pages, convertResultsListToDomain(response.results))
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
}