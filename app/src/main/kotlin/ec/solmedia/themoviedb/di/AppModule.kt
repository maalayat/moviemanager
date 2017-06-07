package ec.solmedia.themoviedb.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ec.solmedia.themoviedb.TheMovieDBApp
import javax.inject.Singleton

@Module
class AppModule(val app: TheMovieDBApp) {

    @Provides
    @Singleton
    fun providesContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun providesApplication(): TheMovieDBApp = app

    @Provides
    @Singleton
    fun providesSharedPreferences(): SharedPreferences {
        return app.getSharedPreferences(TheMovieDBApp.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }
}