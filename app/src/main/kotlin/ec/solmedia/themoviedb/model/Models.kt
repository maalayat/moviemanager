package ec.solmedia.themoviedb.model

import android.os.Parcel
import android.os.Parcelable
import ec.solmedia.themoviedb.commons.adapter.AdapterConstants
import ec.solmedia.themoviedb.commons.adapter.ViewType
import ec.solmedia.themoviedb.commons.extensions.createParcel

data class Media(
        val page: Int,
        val totalResults: Int,
        val totalPages: Int,
        val mediaItems: List<MediaItem>) : Parcelable {

    companion object {
        @JvmField
        val CREATOR = createParcel { Media(it) }
    }

    protected constructor(parcelIn: Parcel) : this(
            parcelIn.readInt(), parcelIn.readInt(), parcelIn.readInt(),
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
                     val title: String?,
                     val name: String?,
                     val overView: String,
                     val firstAirDate: String?,
                     val releaseDate: String?, //val genreIds: Array<Int>,
                     val originalTitle: String?,
                     val originalName: String?,
                     val originalLanguage: String, //val originalCountry: String?,
                     val popularity: Float,
                     val voteAverage: Float,
                     val voteCount: Int,
                     val posterPath: String,
                     val backDropPath: String
) : Parcelable, ViewType {

    companion object {
        @JvmField
        val CREATOR = createParcel { MediaItem(it) }
    }

    constructor(source: Parcel) : this(
            source.readString(), //id
            source.readString(), //title
            source.readString(), //name
            source.readString(), //overView
            source.readString(), //firstAirDate
            source.readString(), //releaseDate
            source.readString(), //originalTitle
            source.readString(), //originalName
            source.readString(), //originalLanguage
            source.readFloat(), //popularity
            source.readFloat(), //voteAverage
            source.readInt(), //voteCount
            source.readString(), //_posterPath
            source.readString()//_backDropPath
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(title)
        dest.writeString(name)
        dest.writeString(overView)
        dest.writeString(firstAirDate)
        dest.writeString(releaseDate)
        dest.writeString(originalTitle)
        dest.writeString(originalName)
        dest.writeString(originalLanguage)
        dest.writeFloat(popularity)
        dest.writeFloat(voteAverage)
        dest.writeInt(voteCount)
        dest.writeString(posterPath)
        dest.writeString(backDropPath)
    }

    override fun getViewType(): Int = AdapterConstants.MEDIA
}
