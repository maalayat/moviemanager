package ec.solmedia.moviemanager.model

import android.os.Parcel
import android.os.Parcelable

data class Movie(
        val id: String,
        val title: String,
        val overView: String,
        val voteAverage: Float,
        val voteCount: Int,
        val posterPath: String,
        val backDropPath: String
) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
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

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            dest.writeString(id)
            dest.writeString(title)
            dest.writeString(overView)
            dest.writeFloat(voteAverage)
            dest.writeInt(voteCount)
            dest.writeString(posterPath)
            dest.writeString(backDropPath)
        }
    }

    override fun describeContents(): Int = 0
}