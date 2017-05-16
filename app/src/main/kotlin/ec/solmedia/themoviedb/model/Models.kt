package ec.solmedia.themoviedb.model

import android.os.Parcel
import android.os.Parcelable
import ec.solmedia.themoviedb.commons.adapter.AdapterConstants
import ec.solmedia.themoviedb.commons.adapter.ViewType
import ec.solmedia.themoviedb.commons.extensions.createParcel

data class Media(
        val page: Int,
        val mediaItems: List<MediaItem>) : Parcelable {

    companion object {
        @JvmField
        val CREATOR = createParcel { Media(it) }
    }

    protected constructor(parcelIn: Parcel) : this(
            parcelIn.readInt(),
            mutableListOf<MediaItem>().apply {
                parcelIn.readTypedList(this, MediaItem.CREATOR)
            }
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(page)
        dest.writeTypedList(mediaItems)
    }

    override fun describeContents(): Int = 0
}

data class MediaItem(val id: String,
                     val title: String,
                     val overView: String,
                     val voteAverage: Float,
                     val voteCount: Int,
                     val _posterPath: String?,
                     val _backDropPath: String?
) : Parcelable, ViewType {

    val posterPath = if (_posterPath != null) "https://image.tmdb.org/t/p/w342$_posterPath" else ""
    val backDropPath = if (_backDropPath != null) "https://image.tmdb.org/t/p/w780$_backDropPath" else ""

    companion object {
        @JvmField
        val CREATOR = createParcel { MediaItem(it) }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readFloat(),
            source.readInt(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(title)
        dest.writeString(overView)
        dest.writeFloat(voteAverage)
        dest.writeInt(voteCount)
        dest.writeString(_posterPath)
        dest.writeString(_backDropPath)
    }

    override fun getViewType(): Int = AdapterConstants.MEDIA
}
