package ec.solmedia.themoviedb.domain

import android.content.SharedPreferences
import android.util.Log
import ec.solmedia.themoviedb.VimoApp
import ec.solmedia.themoviedb.api.VimoAPI
import ec.solmedia.themoviedb.db.MediaItemSql
import ec.solmedia.themoviedb.model.Media
import ec.solmedia.themoviedb.model.MediaItem
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject

class RequestMediaCommand @Inject constructor(
        private val api: VimoAPI,
        private val sPref: SharedPreferences,
        private val dataMapper: DataMapper,
        private val mediaItemSql: MediaItemSql) : Command<Media> {

    private var media = Media()

    override fun execute(mediaType: String, category: String, func: (Media) -> Unit) {
        Log.d("MediaFragment", "actualPage: ${media.page} totalPage: ${media.totalPages}")
        doAsync {
            if (media.page < media.totalPages) {
                val callResponse = api.get(mediaType, category, media.page + 1,
                        sPref.getString(VimoApp.LOCALE_KEY, "en-US")
                )
                val response = callResponse.execute()

                uiThread {
                    if (response.isSuccessful) {
                        val mediaResponse = response.body()
                        val mediaConverted = dataMapper.convertFromApiModel(mediaResponse)
                        media = mediaConverted
                        func(mediaConverted)
                    }
                }
            }
        }
    }

    override fun save(mediaItem: MediaItem) {
        val mediaItemEntity = dataMapper.convertDomainToDataBase(mediaItem)
        mediaItemSql.saveMediaItem(mediaItemEntity)
    }

    override fun delete(mediaItem: MediaItem) {
        mediaItemSql.deleteMediaItem(mediaItem.id)
    }

    override fun isFavorite(id: Int) = mediaItemSql.isFavorite(id)
}