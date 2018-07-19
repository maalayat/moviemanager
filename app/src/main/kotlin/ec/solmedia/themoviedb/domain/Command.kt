package ec.solmedia.themoviedb.domain


interface Command<out T> {
    fun execute(mediaType: String, category: String, error: () -> Unit, successful: (T) -> Unit)
}