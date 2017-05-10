package ec.solmedia.themoviedb.model

import android.os.Parcel
import android.os.Parcelable
import ec.solmedia.themoviedb.commons.adapter.AdapterConstants
import ec.solmedia.themoviedb.commons.adapter.ViewType

data class Media(val page: Int, val media: List<MediaItem>)

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
        @JvmField val CREATOR: Parcelable.Creator<MediaItem> = object : Parcelable.Creator<MediaItem> {
            override fun createFromParcel(source: Parcel): MediaItem = MediaItem(source)
            override fun newArray(size: Int): Array<MediaItem?> = arrayOfNulls(size)
        }
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
