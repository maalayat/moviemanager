package ec.solmedia.themoviedb.domain

import android.content.SharedPreferences
import android.util.Log
import ec.solmedia.themoviedb.VimoApp
import ec.solmedia.themoviedb.api.VimoAPI
import ec.solmedia.themoviedb.db.DetailMediaItemSql
import ec.solmedia.themoviedb.model.DetailMediaItem
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject

class RequestDetailMediaCommand @Inject constructor(
        private val api: VimoAPI,
        private val sPref: SharedPreferences,
        private val dataMapper: DataMapper,
        private val detailMediaItemSql: DetailMediaItemSql) : CommandDetail<DetailMediaItem> {

    override fun execute(mediaType: String, mediaId: Int, callBack: (DetailMediaItem) -> Unit) {
        doAsync {
            val callResponse = api.detail(
                    mediaType,
                    mediaId,
                    sPref.getString(VimoApp.LOCALE_KEY, "en-US"))
            val response = callResponse.execute()

            uiThread {
                if (response.isSuccessful) {
                    val detailResponse = response.body()
                    val detailConverted = dataMapper.convertDetailItemFromApi(detailResponse)
                    callBack(detailConverted)
                } else {
                    Log.e("ReqDetailMediaCommand", "Error al recuperar la informacion de detalle")
                }
            }
        }
    }

    override fun save(detailMediaItem: DetailMediaItem) {
        val detailMediaItemEntity = dataMapper.convertDetailMediaItemToDataBase(detailMediaItem)
        detailMediaItemSql.saveDetailMediaItem(detailMediaItemEntity)
    }

    override fun delete(id: Int) {
        detailMediaItemSql.deleteDetailMediaItem(id)
    }

    override fun isFavorite(id: Int) = detailMediaItemSql.isFavorite(id)
}