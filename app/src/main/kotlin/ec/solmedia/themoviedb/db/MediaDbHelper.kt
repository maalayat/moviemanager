package ec.solmedia.themoviedb.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import ec.solmedia.themoviedb.TheMovieDBApp
import org.jetbrains.anko.db.*

class MediaDbHelper(context: Context = TheMovieDBApp()) :
        ManagedSQLiteOpenHelper(context, MediaDbHelper.DB_NAME, null, MediaDbHelper.DB_VERSION) {
    companion object {

        val DB_NAME = "mediaDb"

        val DB_VERSION = 1
        val instancce by lazy { MediaDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(MediaItemTable.TABLE_NAME, true,
                MediaItemTable.ID to INTEGER,
                MediaItemTable.TITLE to TEXT,
                MediaItemTable.NAME to TEXT,
                MediaItemTable.OVER_VIEW to TEXT,
                MediaItemTable.FIRST_AIR_DATE to TEXT,
                MediaItemTable.RELEASE_DATE to TEXT,
                MediaItemTable.ORIGINAL_TITLE to TEXT,
                MediaItemTable.ORIGINAL_NAME to TEXT,
                MediaItemTable.ORIGINAL_LANGUAGE to TEXT,
                MediaItemTable.POPULARITY to TEXT,
                MediaItemTable.VOTE_AVERAGE to REAL,
                MediaItemTable.VOTE_COUNT to REAL,
                MediaItemTable.POSTER_PATH to TEXT,
                MediaItemTable.BACK_DROP_PATH to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newValue: Int) {
        onCreate(db)
    }
}
