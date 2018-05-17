package ec.solmedia.themoviedb.model

import android.os.Parcelable
import ec.solmedia.themoviedb.commons.adapter.AdapterConstants
import ec.solmedia.themoviedb.commons.adapter.ViewType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Media(
        val page: Int = 0,
        val totalResults: Int = 0,
        val totalPages: Int = 1,
        val mediaItems: List<MediaItem> = emptyList()) : Parcelable

@Parcelize
data class MediaItem(
        val id: Int,
        val title: String?,
        val name: String?,
        val overView: String,
        val firstAirDate: String?,
        val releaseDate: String?,
        val originalTitle: String?,
        val originalName: String?,
        val originalLanguage: String,
        val popularity: Float,
        val voteAverage: Float,
        val voteCount: Int,
        val posterPath: String,
        val backDropPath: String
) : Parcelable, ViewType {
    override fun getViewType(): Int = AdapterConstants.MEDIA
}
