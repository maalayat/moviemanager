package ec.solmedia.themoviedb.db

class DetailMediaItemEntity(
        val map: MutableMap<String, Any?>, val genreEntityList: List<GenreEntity>) {
    var _id: Int by map
    var overview: String by map
    var homepage: String by map
    var name: String? by map
    var inProduction: String? by map
    var originalName: String? by map
    var firstAirDate: String? by map
    var lastAirDate: String? by map
    var numberOfEpisodes: Int? by map
    var numberOfSeasons: Int? by map
    var type: String? by map
    var title: String? by map
    var imdbId: String? by map
    var releaseDate: String? by map
    var revenue: Int? by map
    var originalTitle: String? by map
    var runtime: Int? by map
    var budget: Int? by map
    var tagline: String? by map
    var originalLanguage: String by map
    var popularity: Float by map
    var voteAverage: Float by map
    var voteCount: Int by map
    var status: String by map
    var posterPath: String by map
    var backDropPath: String by map

    constructor(
            id: Int, overview: String, homepage: String, name: String?, inProduction: String?,
            originalName: String?, firstAirDate: String?, lastAirDate: String?,
            numberOfEpisodes: Int?, numberOfSeasons: Int?, type: String?, title: String?,
            imdbId: String?, releaseDate: String?, revenue: Int?, originalTitle: String?,
            runtime: Int?, budget: Int?, tagline: String?, originalLanguage: String,
            popularity: Float, voteAverage: Float, voteCount: Int, status: String,
            posterPath: String, backDropPath: String, genreEntityList: List<GenreEntity>) :
            this(HashMap(), genreEntityList) {
        this._id = id
        this.overview = overview
        this.homepage = homepage
        this.name = name
        this.inProduction = inProduction
        this.originalName = originalName
        this.firstAirDate = firstAirDate
        this.lastAirDate = lastAirDate
        this.numberOfEpisodes = numberOfEpisodes
        this.numberOfSeasons = numberOfSeasons
        this.type = type
        this.title = title
        this.imdbId = imdbId
        this.releaseDate = releaseDate
        this.revenue = revenue
        this.originalTitle = originalTitle
        this.runtime = runtime
        this.budget = budget
        this.tagline = tagline
        this.originalLanguage = originalLanguage
        this.popularity = popularity
        this.voteAverage = voteAverage
        this.voteCount = voteCount
        this.status = status
        this.posterPath = posterPath
        this.backDropPath = backDropPath
    }
}

class GenreEntity(val map: MutableMap<String, Any?>) {
    var _id: Int by map
    var name: String by map
    var detailMediaItemId: Int by map

    constructor(id: Int, name: String, detailMediaItemId: Int) : this(HashMap()) {
        this._id = id
        this.name = name
        this.detailMediaItemId = detailMediaItemId
    }
}