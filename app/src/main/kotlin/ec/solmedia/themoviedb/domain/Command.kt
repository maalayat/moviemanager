package ec.solmedia.themoviedb.domain

import ec.solmedia.themoviedb.model.MediaItem


interface Command<out T> {
    fun execute(mediaType: String, category: String, func: (T) -> Unit)
    fun save(mediaItem: MediaItem)
    fun delete(mediaItem: MediaItem)
    fun isFavorite(id: Int): Boolean
}