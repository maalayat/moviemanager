package ec.solmedia.themoviedb.domain


interface Command<out T> {
    fun execute(mediaType: String, category: String, pageValue: Int): T?
}