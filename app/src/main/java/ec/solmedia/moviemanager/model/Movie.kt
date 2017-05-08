package ec.solmedia.moviemanager.model

import android.os.Parcel
import android.os.Parcelable
import ec.solmedia.moviemanager.commons.adapter.AdapterConstants
import ec.solmedia.moviemanager.commons.adapter.ViewType

data class Movie(
        val id: String,
        val title: String,
        val overView: String,
        val voteAverage: Float,
        val voteCount: Int,
        val _posterPath: String,
        val _backDropPath: String
) : Parcelable, ViewType {

    val posterPath = "https://image.tmdb.org/t/p/w342$_posterPath"
    val backDropPath = "https://image.tmdb.org/t/p/w780$_backDropPath"

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
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

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(title)
        dest.writeString(overView)
        dest.writeFloat(voteAverage)
        dest.writeInt(voteCount)
        dest.writeString(_posterPath)
        dest.writeString(_backDropPath)
    }

    override fun describeContents(): Int = 0

    override fun getViewType(): Int = AdapterConstants.MOVIES
}