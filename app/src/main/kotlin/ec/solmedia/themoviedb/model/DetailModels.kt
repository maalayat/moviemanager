package ec.solmedia.themoviedb.model

import android.os.Parcel
import android.os.Parcelable

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
        val genres: List<Genre>) : Parcelable {
    @Transient
    var isFavorite: Boolean = false

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readValue(Boolean::class.java.classLoader) as Boolean?,
            source.readString(),
            source.readString(),
            source.readString(),
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readString(),
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readString(),
            source.readString(),
            source.readFloat(),
            source.readFloat(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            ArrayList<Genre>().apply { source.readList(this, Genre::class.java.classLoader) }
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(overview)
        writeString(homepage)
        writeString(name)
        writeValue(inProduction)
        writeString(originalName)
        writeString(firstAirDate)
        writeString(lastAirDate)
        writeValue(numberOfEpisodes)
        writeValue(numberOfSeasons)
        writeString(type)
        writeString(title)
        writeString(imdbId)
        writeString(releaseDate)
        writeValue(revenue)
        writeString(originalTitle)
        writeValue(runtime)
        writeValue(budget)
        writeString(tagline)
        writeString(originalLanguage)
        writeFloat(popularity)
        writeFloat(voteAverage)
        writeInt(voteCount)
        writeString(status)
        writeString(posterPath)
        writeString(backDropPath)
        writeList(genres)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DetailMediaItem> = object : Parcelable.Creator<DetailMediaItem> {
            override fun createFromParcel(source: Parcel): DetailMediaItem = DetailMediaItem(source)
            override fun newArray(size: Int): Array<DetailMediaItem?> = arrayOfNulls(size)
        }
    }
}

data class Genre(val id: Int, val name: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(name)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Genre> = object : Parcelable.Creator<Genre> {
            override fun createFromParcel(source: Parcel): Genre = Genre(source)
            override fun newArray(size: Int): Array<Genre?> = arrayOfNulls(size)
        }
    }
}
