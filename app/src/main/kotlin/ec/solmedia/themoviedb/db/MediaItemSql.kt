package ec.solmedia.themoviedb.db

import ec.solmedia.themoviedb.commons.extensions.byId
import ec.solmedia.themoviedb.commons.extensions.deleteById
import ec.solmedia.themoviedb.commons.extensions.parseOpt
import ec.solmedia.themoviedb.commons.extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import javax.inject.Inject

class MediaItemSql @Inject constructor(private val vimoDbHelper: VimoDbHelper) {

    fun saveMediaItem(mediaItemEntity: MediaItemEntity) =
            vimoDbHelper.use {
                with(mediaItemEntity) {
                    insert(MediaItemTable.TABLE_NAME, *map.toVarargArray())
                }
            }

    fun deleteMediaItem(id: Int) = vimoDbHelper.use {
        deleteById(MediaItemTable.TABLE_NAME, id)
    }

    fun searchMediaItem(id: Int) = vimoDbHelper.use {
        select(MediaItemTable.TABLE_NAME)
                .whereSimple("${MediaItemTable.ID} = ?", id.toString())
                .parseOpt { MediaItemEntity(HashMap(it)) }
    }

    fun isFavorite(id: Int): Boolean = vimoDbHelper.use {
        val item = select(MediaItemTable.TABLE_NAME)
                .byId(id)
                .parseOpt { MediaItemEntity(HashMap(it)) }
        item != null
    }
}