package ec.solmedia.themoviedb.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ec.solmedia.themoviedb.VimoApp
import ec.solmedia.themoviedb.db.DetailMediaItemSql
import ec.solmedia.themoviedb.db.VimoDbHelper
import ec.solmedia.themoviedb.domain.DataMapper
import javax.inject.Singleton

@Module
class ApplicationModule(val app: VimoApp) {

    @Provides
    @Singleton
    fun providesContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun providesApplication(): VimoApp = app

    @Provides
    @Singleton
    fun providesSharedPreferences(): SharedPreferences =
            app.getSharedPreferences(VimoApp.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun providesDataMapper(): DataMapper = DataMapper()

    @Provides
    @Singleton
    fun providesVimoDbHelper(context: Context): VimoDbHelper = VimoDbHelper(context)

    @Provides
    @Singleton
    fun providesDetailMediaItemSql(vimoDbHelper: VimoDbHelper) = DetailMediaItemSql(vimoDbHelper)
}