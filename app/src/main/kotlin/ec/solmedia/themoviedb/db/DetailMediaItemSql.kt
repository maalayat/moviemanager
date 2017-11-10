package ec.solmedia.themoviedb.db

import ec.solmedia.themoviedb.commons.extensions.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import javax.inject.Inject

class DetailMediaItemSql @Inject constructor(private val vimoDbHelper: VimoDbHelper) {

    fun saveDetailMediaItem(detailMediaItemEntity: DetailMediaItemEntity) =
            vimoDbHelper.use {
                with(detailMediaItemEntity) {
                    insert(DetailMediaItemTable.TABLE_NAME, *map.toVarargArray())
                    genreEntityList.forEach { insert(GenreTable.TABLE_NAME, *it.map.toVarargArray()) }
                }
            }

    fun deleteDetailMediaItem(id: Int) = vimoDbHelper.use {
        deleteById(DetailMediaItemTable.TABLE_NAME, id)
    }

    fun searchMediaItem(id: Int) = vimoDbHelper.use {
        val genreList = select(GenreTable.TABLE_NAME)
                .whereSimple("${GenreTable.DETAIL_MEDIA_ITEM_ID} = ?", id.toString())
                .parseList { GenreEntity(HashMap(it)) }

        select(DetailMediaItemTable.TABLE_NAME)
                .whereSimple("${DetailMediaItemTable.ID} = ?", id.toString())
                .parseOpt { DetailMediaItemEntity(HashMap(it), genreList) }
    }

    fun isFavorite(id: Int): Boolean = vimoDbHelper.use {
        val item = select(DetailMediaItemTable.TABLE_NAME)
                .byId(id)
                .parseOpt { DetailMediaItemEntity(HashMap(it), emptyList()) }
        item != null
    }
}