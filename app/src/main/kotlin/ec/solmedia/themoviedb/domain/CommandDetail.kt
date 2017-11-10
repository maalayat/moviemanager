package ec.solmedia.themoviedb.domain

import ec.solmedia.themoviedb.model.DetailMediaItem

interface CommandDetail<out T> {
    fun execute(mediaType: String, mediaId: Int, callBack: (T) -> Unit)
    fun save(detailMediaItem: DetailMediaItem)
    fun delete(id: Int)
    fun isFavorite(id: Int): Boolean
}