package ec.solmedia.themoviedb.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import javax.inject.Inject

class VimoDbHelper @Inject constructor(context: Context) :
        ManagedSQLiteOpenHelper(context, VimoDbHelper.DB_NAME, null, VimoDbHelper.DB_VERSION) {

    companion object {
        val DB_NAME = "vimo.db"
        val DB_VERSION = 1
        //val instance by lazy { VimoDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
                DetailMediaItemTable.TABLE_NAME, true,
                DetailMediaItemTable.ID to INTEGER + PRIMARY_KEY,
                DetailMediaItemTable.OVER_VIEW to TEXT,
                DetailMediaItemTable.HOMEPAGE to TEXT,
                DetailMediaItemTable.NAME to TEXT,
                DetailMediaItemTable.IN_PRODUCTION to TEXT,
                DetailMediaItemTable.ORIGINAL_NAME to TEXT,
                DetailMediaItemTable.FIRST_AIR_DATE to TEXT,
                DetailMediaItemTable.LAST_AIR_DATE to TEXT,
                DetailMediaItemTable.NUMBER_OF_EPISODES to INTEGER,
                DetailMediaItemTable.NUMBER_OF_SEASONS to INTEGER,
                DetailMediaItemTable.TYPE to TEXT,
                DetailMediaItemTable.TITLE to TEXT,
                DetailMediaItemTable.IMDB_ID to TEXT,
                DetailMediaItemTable.RELEASE_DATE to TEXT,
                DetailMediaItemTable.REVENUE to INTEGER,
                DetailMediaItemTable.ORIGINAL_TITLE to TEXT,
                DetailMediaItemTable.RUNTIME to INTEGER,
                DetailMediaItemTable.BUDGET to INTEGER,
                DetailMediaItemTable.TAGLINE to TEXT,
                DetailMediaItemTable.ORIGINAL_LANGUAGE to TEXT,
                DetailMediaItemTable.POPULARITY to REAL,
                DetailMediaItemTable.VOTE_AVERAGE to REAL,
                DetailMediaItemTable.VOTE_COUNT to REAL,
                DetailMediaItemTable.STATUS to REAL,
                DetailMediaItemTable.POSTER_PATH to TEXT,
                DetailMediaItemTable.BACK_DROP_PATH to TEXT)

        db.createTable(
                GenreTable.TABLE_NAME, true,
                GenreTable.ID to INTEGER,
                GenreTable.NAME to TEXT,
                GenreTable.DETAIL_MEDIA_ITEM_ID to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newValue: Int) {
        onCreate(db)
    }
}